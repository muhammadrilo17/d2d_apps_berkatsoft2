package com.rilodev.d2dapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel(
    val userId: String,
    val email: String,
    val name: String,
): Parcelable