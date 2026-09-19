package com.amahmouddm.moviemate.data.movies.di

import com.amahmouddm.moviemate.data.movies.datasource.network.client.configureNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkClientModule {
    @Provides
    @Singleton
    fun provideTmdbNetworkClient(): HttpClient {
        return HttpClient(OkHttp) {
            configureNetworkClient()
        }
    }
}
