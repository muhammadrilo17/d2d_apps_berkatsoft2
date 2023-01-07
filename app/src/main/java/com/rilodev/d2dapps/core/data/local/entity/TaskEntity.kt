package com.rilodev.d2dapps.core.data.local.entity

import android.provider.BaseColumns
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taskTable")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = BaseColumns._ID, index = true) var id: Int? = null,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "timeDateCreated") val timeDateCreated: String,
    @ColumnInfo(name = "timeDateEnded") var timeDateEnded: String?,
)