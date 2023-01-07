package com.rilodev.d2dapps.core.domain.usecase.task

import com.rilodev.d2dapps.core.domain.usecase.task.addtask.AddTaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.loadtask.LoadTaskByDateUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.loadtask.LoadTaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.updatetask.UpdateTaskUseCase

data class TaskUseCase(
    val addTaskUseCase: AddTaskUseCase,
    val loadTaskUseCase: LoadTaskUseCase,
    val updateTaskUseCase: UpdateTaskUseCase,
    val loadTaskByDateUseCase: LoadTaskByDateUseCase,
)