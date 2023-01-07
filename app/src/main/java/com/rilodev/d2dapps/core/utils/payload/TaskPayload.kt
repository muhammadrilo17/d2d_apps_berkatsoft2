package com.rilodev.d2dapps.core.utils.payload

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskPayload(
    val description: String,
    val timeDateCreated: String,
    var timeDateEnded: String?,
): Parcelable