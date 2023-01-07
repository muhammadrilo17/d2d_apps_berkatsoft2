package com.rilodev.d2dapps.core.domain.usecase.auth.loggedin

import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import javax.inject.Inject

class LoggedInUseCase @Inject constructor(private val repository: IAuthRepository) {
    suspend operator fun invoke(): Boolean {
        return repository.isLoggedIn()
    }
}