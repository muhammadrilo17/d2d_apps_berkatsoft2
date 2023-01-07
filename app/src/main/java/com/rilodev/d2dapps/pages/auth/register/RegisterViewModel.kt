package com.rilodev.d2dapps.pages.auth.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.usecase.auth.AuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val useCase: AuthUseCase, private val useCaseUser: UserUseCase): ViewModel() {
    private var resultRegister = MutableLiveData<Resource<String>>()
    private var resultSaveUser = MutableLiveData<Resource<Boolean>>()
    fun reset(){
        resultRegister = MutableLiveData()
    }
    fun register(email: String, name: String, password: String): LiveData<Resource<String>> {
        viewModelScope.launch {
            resultRegister.value = useCase.registerAuthUseCase(email, name, password)
        }
        return resultRegister
    }
    fun saveUser(uid: String, email: String, name: String): LiveData<Resource<Boolean>> {
        viewModelScope.launch {
            resultSaveUser.value = useCaseUser.saveUserUseCase(uid, email, name)
        }
        return resultSaveUser
    }
}