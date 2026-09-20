package com.amahmouddm.moviemate.core.network.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.okhttp.OkHttp

@Module
@InstallIn(SingletonComponent::class)
object NetworkEngineFactoryModule {
    @Provides
    fun provideNetworkEngineFactory(): HttpClientEngineFactory<HttpClientEngineConfig> =
        OkHttp
}
