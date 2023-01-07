package com.rilodev.d2dapps.core.domain.usecase.user

import com.rilodev.d2dapps.core.domain.usecase.user.loaduser.LoadUserUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.saveuser.SaveUserUseCase

data class UserUseCase(
    val saveUserUseCase: SaveUserUseCase,
    val loadUserUseCase: LoadUserUseCase,
)