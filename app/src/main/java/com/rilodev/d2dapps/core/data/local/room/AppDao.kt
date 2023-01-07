package com.rilodev.d2dapps.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    @Query("SELECT * FROM taskTable")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM userTable WHERE uid = :uid")
    fun getUserById(uid: String): Flow<UserEntity>

    @Update
    suspend fun updateTask(taskEntity: TaskEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPresence(presenceEntity: PresenceEntity)

    @Query("SELECT EXISTS(SELECT * FROM presenceTable WHERE date = :date)")
    fun isPresenceExist(date: String): Flow<Boolean>

    @Query("SELECT * FROM taskTable WHERE timeDateCreated LIKE '%' || :date || '%'")
    fun getTasksByDate(date: String): Flow<List<TaskEntity>>


}