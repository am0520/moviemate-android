package com.amahmouddm.moviemate.data.movies.di

import com.amahmouddm.moviemate.data.movies.datasource.network.client.NetworkClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkClientModule {
    @Provides
    @Singleton
    fun provideTmdbNetworkClient(): HttpClient {
        return NetworkClientFactory.create()
    }
}
