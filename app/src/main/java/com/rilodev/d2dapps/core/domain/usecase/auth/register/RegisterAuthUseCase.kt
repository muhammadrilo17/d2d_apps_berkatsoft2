package com.rilodev.d2dapps.core.domain.usecase.auth.register

import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import javax.inject.Inject

class RegisterAuthUseCase @Inject constructor(private val repository: IAuthRepository) {
    suspend operator fun invoke(email: String, name: String,  password: String): Resource<String> {
        return repository.register(email, name, password)
    }
}