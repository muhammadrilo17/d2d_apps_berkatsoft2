package com.rilodev.d2dapps.core.domain.usecase.presence

import com.rilodev.d2dapps.core.domain.usecase.presence.insert.InsertPresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.presence.isexist.IsExistPresenceUseCase

data class PresenceUseCase(
    val insertPresenceUseCase: InsertPresenceUseCase,
    val isExistPresenceUseCase: IsExistPresenceUseCase,
)