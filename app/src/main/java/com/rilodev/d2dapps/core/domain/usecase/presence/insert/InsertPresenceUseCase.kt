package com.rilodev.d2dapps.core.domain.usecase.presence.insert

import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.domain.repository.IPresenceRepository
import javax.inject.Inject

class InsertPresenceUseCase @Inject constructor(private val repository: IPresenceRepository){
    suspend operator fun invoke(presenceEntity: PresenceEntity) {
        repository.insertPresence(presenceEntity)
    }
}