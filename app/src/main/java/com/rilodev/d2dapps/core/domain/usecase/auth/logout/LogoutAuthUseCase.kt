package com.rilodev.d2dapps.core.domain.usecase.auth.logout

import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import javax.inject.Inject

class LogoutAuthUseCase @Inject constructor(private val repository: IAuthRepository) {
    operator fun invoke() {
        repository.logout()
    }
}