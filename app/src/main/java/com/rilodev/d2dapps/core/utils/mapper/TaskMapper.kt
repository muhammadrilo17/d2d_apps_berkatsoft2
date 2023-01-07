package com.rilodev.d2dapps.core.utils.mapper

import com.rilodev.d2dapps.core.data.local.entity.TaskEntity
import com.rilodev.d2dapps.core.domain.model.TaskModel

object TaskMapper {
    fun entityToDomain(input: TaskEntity): TaskModel {
        return TaskModel(
            id = input.id!!,
            description = input.description,
            timeDateCreated = input.timeDateCreated,
            timeDateEnded = input.timeDateEnded,
        )
    }

    // TODO: NOT USE FOR NOW
//    fun domainToEntity(input: TaskModel): TaskEntity {
//        return TaskEntity(
//            id = null,
//            description = input.description,
//            timeDateCreated = input.timeDateCreated,
//            timeDateEnded = input.timeDateEnded,
//        )
//    }
}