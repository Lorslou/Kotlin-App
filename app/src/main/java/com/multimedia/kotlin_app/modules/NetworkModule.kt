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
 * Módulo que nos proveerá dependencias de librerías o de clases que contienen interfaces
 * -> Los módulos proveen dependencias que pueden ser usadas en otros lugares del código
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