package com.amahmouddm.moviemate.core.network.di

import com.amahmouddm.moviemate.core.common.ServerConfig
import com.amahmouddm.moviemate.core.network.configureNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkClientModule {
    @Provides
    @Singleton
    fun provideNetworkClient(
        clientEngineFactory: HttpClientEngineFactory<@JvmSuppressWildcards HttpClientEngineConfig>,
        serverConfig: ServerConfig,
    ): HttpClient = HttpClient(clientEngineFactory) {
        configureNetworkClient(serverConfig = serverConfig)
    }
}
