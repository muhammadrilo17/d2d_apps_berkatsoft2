package com.rilodev.d2dapps.core.domain.usecase.auth

import com.rilodev.d2dapps.core.domain.usecase.auth.loggedin.LoggedInUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.login.LoginAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.login.LoginSessionAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.logout.LogoutAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.register.RegisterAuthUseCase

data class AuthUseCase(
    val loginAuthUseCase: LoginAuthUseCase,
    val registerAuthUseCase: RegisterAuthUseCase,
    val loginSessionAuthUseCase: LoginSessionAuthUseCase,
    val logoutAuthUseCase: LogoutAuthUseCase,
    val loggedInUseCase: LoggedInUseCase,
)