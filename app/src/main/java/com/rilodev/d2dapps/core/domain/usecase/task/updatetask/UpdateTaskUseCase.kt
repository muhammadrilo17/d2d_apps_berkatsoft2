package com.rilodev.d2dapps.core.domain.usecase.task.updatetask

import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val repository: ITaskRepository) {
    suspend operator fun invoke(task: TaskEntity) {
        repository.updateTask(task)
    }
}