package com.rilodev.d2dapps.core.data.local.entity

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "presenceTable")
data class PresenceEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = BaseColumns._ID, index = true) var id: Int? = null,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "time") val time: String,
)