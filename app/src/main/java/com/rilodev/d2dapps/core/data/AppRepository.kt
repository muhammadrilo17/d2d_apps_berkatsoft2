package com.rilodev.d2dapps.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.rilodev.d2dapps.core.data.local.LocalDataSource
import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.model.TaskModel
import com.rilodev.d2dapps.core.domain.model.UserModel
import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import com.rilodev.d2dapps.core.domain.repository.IPresenceRepository
import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import com.rilodev.d2dapps.core.domain.repository.IUserRepository
import com.rilodev.d2dapps.core.utils.Constants
import com.rilodev.d2dapps.core.utils.mapper.TaskMapper
import com.rilodev.d2dapps.core.utils.payload.TaskPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    db: FirebaseFirestore,
    private val localDataSource: LocalDataSource,
) : IAuthRepository, ITaskRepository, IUserRepository, IPresenceRepository {
    private val userCollectionReference = db.collection(Constants.USERS_COLLECTION)
    private val taskCollectionReference = db.collection(Constants.TASK_COLLECTION)

    private val _userData = MutableLiveData<UserModel>()
    override val userData: LiveData<UserModel> = _userData

    override suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Resource<String> {
        return try {
            withContext(Dispatchers.IO) {
                val uid = createAccount(email, password)
                logout()
                Resource.Success(uid.user?.uid.toString())
            }
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    private suspend fun createAccount(email: String, password: String): AuthResult {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }


    override suspend fun saveUserToCollection(
        uid: String, email: String, name: String
    ): Resource<Boolean> {
        val user = hashMapOf(
            "name" to name,
            "email" to email,
        )
        userCollectionReference.document(uid).set(user, SetOptions.merge()).await()
        return Resource.Success(firebaseAuth.currentUser?.uid != null)
    }

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if (firebaseAuth.currentUser?.uid != null) {
                val data = loadUserData(firebaseAuth.currentUser?.uid.toString())
                Log.d("RESULT DATA", data.toString())
                _userData.postValue(data)
                Resource.Success(true)
            } else Resource.Error("Login Gagal!")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }

    }

    override suspend fun isLoggedIn(): Boolean {
        val result = firebaseAuth.currentUser?.uid != null
        if (result) {
            val data = loadUserData(firebaseAuth.currentUser?.uid.toString())
            _userData.postValue(data)
            return true
        }
        return false
    }


    override fun logout() = firebaseAuth.signOut()

    override suspend fun addNewTask(task: TaskPayload) {
        localDataSource.insertTask(
            TaskEntity(
                null,
                task.description,
                task.timeDateCreated,
                task.timeDateEnded
            )
        )
    }

    override fun loadTasks(): Flow<ArrayList<TaskEntity>> {
        return flow {
            val result = localDataSource.getAllTask().first()
            emit(ArrayList(result))
        }
    }

    override fun loadTasksByDate(date: String): Flow<ArrayList<TaskEntity>> {
        return flow {
            val result = localDataSource.getTasksByDate(date).first()
            emit(ArrayList(result))
        }
    }

    private suspend fun loadUserData(uid: String): UserModel {
        val data = userCollectionReference.document(uid).get().await()

        return UserModel(uid, data.data?.get("email").toString(), data.data?.get("name").toString())
    }

    override suspend fun updateTask(task: TaskEntity) {
        localDataSource.updateTask(task)
    }

    override suspend fun insertPresence(presenceEntity: PresenceEntity) {
        localDataSource.insertPresence(presenceEntity)
    }

    override fun isPresenceExist(date: String): Flow<Boolean> {
        return flow { emit(localDataSource.isPresenceExist(date).first()) }
    }


}