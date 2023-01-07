package com.rilodev.d2dapps.core.domain.usecase.task.addtask

import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import com.rilodev.d2dapps.core.utils.payload.TaskPayload
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val repository: ITaskRepository) {
    suspend operator fun invoke(task: TaskPayload) {
        return repository.addNewTask(task)
    }
}