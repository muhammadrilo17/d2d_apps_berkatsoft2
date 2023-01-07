package com.rilodev.d2dapps.core.domain.usecase.user.loaduser

import androidx.lifecycle.LiveData
import com.rilodev.d2dapps.core.domain.model.UserModel
import com.rilodev.d2dapps.core.domain.repository.IUserRepository
import javax.inject.Inject

class LoadUserUseCase @Inject constructor(private val repository: IUserRepository) {
    operator fun invoke(): LiveData<UserModel> = repository.userData
}