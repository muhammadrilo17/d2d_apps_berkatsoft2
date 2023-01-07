package com.rilodev.d2dapps.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rilodev.d2dapps.core.data.local.entity.PresenceEntity
import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, TaskEntity::class, PresenceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}