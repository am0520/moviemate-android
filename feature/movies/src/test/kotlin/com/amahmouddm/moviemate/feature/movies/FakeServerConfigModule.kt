package com.amahmouddm.moviemate.feature.movies

import com.amahmouddm.moviemate.core.common.ServerConfig
import com.amahmouddm.moviemate.feature.movies.di.ServerConfigModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [ServerConfigModule::class],
)
internal object FakeServerConfigModule {
    @Provides
    fun provideFakeServerConfig(): ServerConfig = ServerConfig.create(
        baseUrl = "https://example.com",
        accessToken = "test-access-token",
    )
}
