package com.amahmouddm.moviemate.core.networktest

import com.amahmouddm.moviemate.core.network.di.NetworkEngineFactoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkEngineFactoryModule::class],
)
internal object TestNetworkEngineFactoryModule {
    @Provides
    fun provideTestNetworkEngineFactory(
        testNetworkEngineFactory: TestNetworkEngine,
    ): HttpClientEngineFactory<HttpClientEngineConfig> =
        testNetworkEngineFactory
}
