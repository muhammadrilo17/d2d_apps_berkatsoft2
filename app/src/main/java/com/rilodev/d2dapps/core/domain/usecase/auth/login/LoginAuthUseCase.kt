package com.rilodev.d2dapps.core.domain.usecase.auth.login

import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginAuthUseCase @Inject constructor(private val repository: IAuthRepository) {
    suspend operator fun invoke(email: String,  password: String): Resource<Boolean> {
        return repository.login(email, password)
    }
}