package com.rilodev.d2dapps.core.domain.usecase.auth.login

import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import javax.inject.Inject

class LoginSessionAuthUseCase @Inject constructor(private val repository: IAuthRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isLoggedIn()
    }
}