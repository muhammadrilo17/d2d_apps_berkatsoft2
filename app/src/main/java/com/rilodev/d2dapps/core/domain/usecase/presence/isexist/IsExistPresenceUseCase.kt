package com.rilodev.d2dapps.core.domain.usecase.presence.isexist

import com.rilodev.d2dapps.core.domain.repository.IPresenceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsExistPresenceUseCase @Inject constructor(private val repository: IPresenceRepository) {
    operator fun invoke(date: String): Flow<Boolean> {
        return repository.isPresenceExist(date)
    }
}