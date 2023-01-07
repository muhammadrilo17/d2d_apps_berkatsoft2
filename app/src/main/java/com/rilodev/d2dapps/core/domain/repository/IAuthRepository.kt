package com.rilodev.d2dapps.core.domain.repository

import com.rilodev.d2dapps.core.data.Resource

interface IAuthRepository {
    suspend fun register(
        email: String,
        name: String,
        password: String,
    ): Resource<String>

    suspend fun login(email: String, password: String): Resource<Boolean>

    suspend fun isLoggedIn(): Boolean

    fun logout()
}