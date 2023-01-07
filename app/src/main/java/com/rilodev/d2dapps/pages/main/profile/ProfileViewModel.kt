package com.rilodev.d2dapps.pages.main.profile

import androidx.lifecycle.ViewModel
import com.rilodev.d2dapps.core.domain.usecase.auth.AuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val useCase: AuthUseCase, useCaseUser: UserUseCase): ViewModel() {
    val userData = useCaseUser.loadUserUseCase()

    fun logout() = useCase.logoutAuthUseCase()
}