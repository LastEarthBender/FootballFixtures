package com.example.footballfixtures.di

import android.content.Context
import androidx.room.Room
import com.example.footballfixtures.data.db.FootballDatabase
import com.example.footballfixtures.data.db.MatchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideFootballDatabase(@ApplicationContext context: Context): FootballDatabase =
        Room.databaseBuilder(
            context,
            FootballDatabase::class.java,
            "football_db"
        ).build()

    @Provides
    @Singleton
    fun provideMatchDao(database: FootballDatabase): MatchDao =
        database.matchDao()
}
