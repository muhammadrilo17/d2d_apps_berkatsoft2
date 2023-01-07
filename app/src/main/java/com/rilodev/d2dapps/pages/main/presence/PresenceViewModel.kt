package com.rilodev.d2dapps.pages.main.presence

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.domain.usecase.presence.PresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PresenceViewModel @Inject constructor(private val useCase: UserUseCase, private val presenceUseCase: PresenceUseCase): ViewModel() {
    fun isPresenceExist(date: String): LiveData<Boolean> {
        return presenceUseCase.isExistPresenceUseCase(date).asLiveData()
    }
    fun insertPresence(presenceEntity: PresenceEntity) {
        viewModelScope.launch {
            presenceUseCase.insertPresenceUseCase(presenceEntity)
        }
    }
}