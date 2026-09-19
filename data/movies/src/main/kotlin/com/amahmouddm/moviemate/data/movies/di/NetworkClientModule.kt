package com.amahmouddm.moviemate.data.movies.di

import com.amahmouddm.moviemate.core.common.ServerConfig
import com.amahmouddm.moviemate.core.network.configureNetworkClient
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
    fun provideNetworkClient(
        serverConfig: ServerConfig,
    ): HttpClient {
        return HttpClient(OkHttp) {
            configureNetworkClient(
                serverConfig = serverConfig,
            )
        }
    }
}
