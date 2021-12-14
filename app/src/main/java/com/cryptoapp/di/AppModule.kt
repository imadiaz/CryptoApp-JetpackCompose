package com.cryptoapp.di

import com.cryptoapp.data.network.CoinApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.bitso.com/v3/")
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder().create()
                )
            ).build()


    @Provides
    @Singleton
    fun provideApiService(
        retrofit: Retrofit
    ): CoinApiService = retrofit.create(CoinApiService::class.java)
}