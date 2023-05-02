package com.multimedia.kotlin_app.modules

import android.content.Context
import androidx.room.Room
import com.multimedia.kotlin_app.data.database.AgentDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DB_NAME = "agent_data_table"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AgentDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideAgentDao(db:AgentDatabase) = db.getAgentDao()
}