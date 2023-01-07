package com.rilodev.d2dapps.pages.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rilodev.d2dapps.core.data.Resource
import com.rilodev.d2dapps.core.domain.usecase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthUseCase): ViewModel() {
    private var resultLogin = MutableLiveData<Resource<Boolean>>()
    private var resultLoggedIn = MutableLiveData<Boolean>()
    fun reset() {
        resultLogin = MutableLiveData<Resource<Boolean>>()
        resultLoggedIn = MutableLiveData<Boolean>()
    }
    fun login(email: String, password: String): LiveData<Resource<Boolean>> {
        viewModelScope.launch {
            resultLogin.value = useCase.loginAuthUseCase(email, password)
        }
        return resultLogin
    }

    fun isLoggedIn(): LiveData<Boolean> {
        viewModelScope.launch {
            resultLoggedIn.value = useCase.loggedInUseCase()
        }
        return resultLoggedIn
    }
}