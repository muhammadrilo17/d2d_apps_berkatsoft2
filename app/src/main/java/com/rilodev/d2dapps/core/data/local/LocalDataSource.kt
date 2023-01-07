package com.rilodev.d2dapps.core.data.local

import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.data.local.room.AppDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val appDao: AppDao) {
    fun getAllTask(): Flow<List<TaskEntity>> = appDao.getAllTask()
    suspend fun insertTask(taskEntity: TaskEntity) = appDao.insertTask(taskEntity)
    suspend fun updateTask(taskEntity: TaskEntity) = appDao.updateTask(taskEntity)
    suspend fun insertPresence(presenceEntity: PresenceEntity) = appDao.insertPresence(presenceEntity)
    fun isPresenceExist(date: String): Flow<Boolean> = appDao.isPresenceExist(date)
    fun getTasksByDate(date: String): Flow<List<TaskEntity>> = appDao.getTasksByDate(date)
}