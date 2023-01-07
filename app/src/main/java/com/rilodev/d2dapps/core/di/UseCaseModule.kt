package com.rilodev.d2dapps.core.di

import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import com.rilodev.d2dapps.core.domain.repository.IPresenceRepository
import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import com.rilodev.d2dapps.core.domain.repository.IUserRepository
import com.rilodev.d2dapps.core.domain.usecase.auth.AuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.loggedin.LoggedInUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.login.LoginAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.login.LoginSessionAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.logout.LogoutAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.auth.register.RegisterAuthUseCase
import com.rilodev.d2dapps.core.domain.usecase.presence.PresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.presence.insert.InsertPresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.presence.isexist.IsExistPresenceUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.saveuser.SaveUserUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.TaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.addtask.AddTaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.loadtask.LoadTaskByDateUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.loadtask.LoadTaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.task.updatetask.UpdateTaskUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.UserUseCase
import com.rilodev.d2dapps.core.domain.usecase.user.loaduser.LoadUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideAuthUseCase(
        repository: IAuthRepository
    ): AuthUseCase {
        return AuthUseCase(
            loginAuthUseCase = LoginAuthUseCase(repository),
            registerAuthUseCase = RegisterAuthUseCase(repository),
            loginSessionAuthUseCase = LoginSessionAuthUseCase(repository),
            logoutAuthUseCase = LogoutAuthUseCase(repository),
            loggedInUseCase = LoggedInUseCase(repository),
        )
    }

    @Singleton
    @Provides
    fun provideTaskUseCase(
        repository: ITaskRepository
    ): TaskUseCase {
        return TaskUseCase(
            addTaskUseCase = AddTaskUseCase(repository),
            loadTaskUseCase = LoadTaskUseCase(repository),
            updateTaskUseCase = UpdateTaskUseCase(repository),
            loadTaskByDateUseCase = LoadTaskByDateUseCase(repository),
        )
    }

    @Singleton
    @Provides
    fun provideUserUseCase(
        repository: IUserRepository
    ): UserUseCase {
        return UserUseCase(
            saveUserUseCase = SaveUserUseCase(repository),
            loadUserUseCase = LoadUserUseCase(repository),
        )
    }

    @Singleton
    @Provides
    fun providePresenceUseCase(
        repository: IPresenceRepository
    ): PresenceUseCase {
        return PresenceUseCase(
            insertPresenceUseCase = InsertPresenceUseCase(repository),
            isExistPresenceUseCase = IsExistPresenceUseCase(repository)
        )
    }
}