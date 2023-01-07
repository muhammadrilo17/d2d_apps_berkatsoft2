package com.rilodev.d2dapps.core.domain.repository

import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.utils.payload.TaskPayload
import kotlinx.coroutines.flow.Flow

interface ITaskRepository {
    suspend fun addNewTask(task: TaskPayload)
    fun loadTasks(): Flow<ArrayList<TaskEntity>>
    suspend fun updateTask(task: TaskEntity)
    fun loadTasksByDate(date: String): Flow<ArrayList<TaskEntity>>
}