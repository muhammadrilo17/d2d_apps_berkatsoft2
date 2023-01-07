package com.rilodev.d2dapps.pages.main.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.usecase.presence.PresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.TaskUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val useCase: TaskUseCase, private val presenceUseCase: PresenceUseCase): ViewModel() {
    fun loadTasksByDate(date: String): LiveData<ArrayList<TaskEntity>> {
        return useCase.loadTaskByDateUseCase(date).asLiveData()
    }
    fun isPresenceExist(date: String): LiveData<Boolean> {
        return presenceUseCase.isExistPresenceUseCase(date).asLiveData()
    }
}