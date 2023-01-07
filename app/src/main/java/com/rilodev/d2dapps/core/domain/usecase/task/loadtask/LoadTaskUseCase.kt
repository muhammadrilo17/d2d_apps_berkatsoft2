package com.rilodev.d2dapps.core.domain.usecase.task.loadtask

import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.model.TaskModel
import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadTaskUseCase @Inject constructor(private val repository: ITaskRepository) {
    operator fun invoke(): Flow<ArrayList<TaskEntity>> {
        return repository.loadTasks()
    }
}