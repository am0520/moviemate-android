package com.amahmouddm.moviemate.core.datatest

import com.amahmouddm.moviemate.data.movies.di.NetworkEngineFactoryModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.config
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkEngineFactoryModule::class],
)
internal object FakeNetworkEngineFactoryModule {
    @Provides
    fun provideFakeNetworkEngineFactory(
        fakeServer: FakeServer,
    ): HttpClientEngineFactory<HttpClientEngineConfig> =
        MockEngine.config {
            addHandler { request ->
                val response = fakeServer.handle(request)

                respond(
                    content = response.body,
                    status = response.status,
                    headers = response.headers,
                )
            }
        }
}
