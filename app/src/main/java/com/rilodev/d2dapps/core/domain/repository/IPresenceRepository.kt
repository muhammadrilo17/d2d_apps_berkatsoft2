package com.rilodev.d2dapps.core.domain.repository

import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import kotlinx.coroutines.flow.Flow

interface IPresenceRepository {
    suspend fun insertPresence(presenceEntity: PresenceEntity)
    fun isPresenceExist(date: String): Flow<Boolean>
}