package com.rilodev.d2dapps.core.domain.repository

import androidx.lifecycle.LiveData
import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.model.UserModel

interface IUserRepository {
    val userData: LiveData<UserModel>
    suspend fun saveUserToCollection(uid: String, email: String, name: String): Resource<Boolean>
}