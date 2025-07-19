package com.example.footballfixtures.di

import com.example.footballfixtures.data.api.FootballApiService
import com.example.footballfixtures.data.db.MatchDao
import com.example.footballfixtures.data.repository.FixturesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideFixturesRepository(
        matchDao: MatchDao,
        apiService: FootballApiService,
    ): FixturesRepository = FixturesRepository(matchDao, apiService)
}
