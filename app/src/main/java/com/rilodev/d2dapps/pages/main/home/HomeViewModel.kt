package com.rilodev.d2dapps.pages.main.home

import androidx.lifecycle.*
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.usecase.task.TaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.UserUseCase
import com.rilodev.d2dapps.core.utils.payload.TaskPayload
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: TaskUseCase, useCaseUser: UserUseCase): ViewModel() {
    val userData = useCaseUser.loadUserUseCase()
    fun addNewTask(task: TaskPayload) {
        viewModelScope.launch {
            useCase.addTaskUseCase(task)
        }
    }

    fun loadTasks(): LiveData<ArrayList<TaskEntity>> {
        return useCase.loadTaskUseCase().asLiveData()
    }

    fun updateTask(task: TaskEntity) {
        viewModelScope.launch {
            useCase.updateTaskUseCase(task)
        }
    }
}