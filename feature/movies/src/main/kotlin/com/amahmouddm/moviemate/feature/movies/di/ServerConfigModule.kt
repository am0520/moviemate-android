package com.amahmouddm.moviemate.feature.movies.di

import com.amahmouddm.moviemate.core.common.ServerConfig
import com.amahmouddm.moviemate.feature.movies.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

private const val TMDB_BASE_URL = BuildConfig.TMDB_BASE_URL
private const val TMDB_ACCESS_TOKEN = BuildConfig.TMDB_ACCESS_TOKEN

@Module
@InstallIn(SingletonComponent::class)
internal object ServerConfigModule {
    @Provides
    fun provideServerConfig(): ServerConfig = ServerConfig.create(
        baseUrl = TMDB_BASE_URL,
        accessToken = TMDB_ACCESS_TOKEN,
    )
}
