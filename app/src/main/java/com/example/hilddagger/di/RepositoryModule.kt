package com.example.hilddagger.di

import com.example.hilddagger.remote.repository.IRepository
import com.example.hilddagger.remote.repository.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(
        repositoryImpl: RepositoryImp
    ): IRepository
}
