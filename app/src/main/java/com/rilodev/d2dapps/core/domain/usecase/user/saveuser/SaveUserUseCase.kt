package com.rilodev.d2dapps.core.domain.usecase.user.saveuser

import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.repository.IUserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val repository: IUserRepository) {
    suspend operator fun invoke(uid: String, email: String, name: String): Resource<Boolean> {
        return repository.saveUserToCollection(uid, email, name)
    }
}