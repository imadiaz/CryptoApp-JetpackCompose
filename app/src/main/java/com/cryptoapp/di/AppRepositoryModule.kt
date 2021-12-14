package com.cryptoapp.di

import com.cryptoapp.domain.repository.CoinsRepository
import com.cryptoapp.domain.repository.CoinsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class  AppRepositoryModule {

    @Binds
    abstract fun bindsCoinRepository(
        repositoryImp: CoinsRepositoryImp
    ): CoinsRepository

}