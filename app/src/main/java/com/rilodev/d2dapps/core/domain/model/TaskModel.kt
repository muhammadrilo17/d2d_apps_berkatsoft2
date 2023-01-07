package com.rilodev.d2dapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModel(
    val id: Int,
    val description: String,
    val timeDateCreated: String,
    var timeDateEnded: String?,
): Parcelable