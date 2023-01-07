package com.rilodev.d2dapps.core.di

import com.rilodev.d2dapps.core.data.AppRepository
import com.rilodev.d2dapps.core.domain.repository.IAuthRepository
import com.rilodev.d2dapps.core.domain.repository.IPresenceRepository
import com.rilodev.d2dapps.core.domain.repository.ITaskRepository
import com.rilodev.d2dapps.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(loginRepository: AppRepository): IAuthRepository

    @Binds
    abstract fun provideTaskRepository(loginRepository: AppRepository): ITaskRepository

    @Binds
    abstract fun provideUserRepository(loginRepository: AppRepository): IUserRepository

    @Binds
    abstract fun providePresenceRepository(presenceRepository: AppRepository): IPresenceRepository
}
