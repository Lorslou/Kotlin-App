package com.multimedia.kotlin_app.modules

import com.multimedia.kotlin_app.data.network.ValorantApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This module will provide dependencies for libraries or classes that contain interfaces.
 * -> Modules provide dependencies that can be used in other parts of the code
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideValorantApiClient(retrofit: Retrofit): ValorantApiClient {
        return retrofit.create(ValorantApiClient::class.java)
    }
}