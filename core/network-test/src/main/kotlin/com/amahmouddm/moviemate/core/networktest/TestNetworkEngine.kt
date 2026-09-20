package com.amahmouddm.moviemate.core.networktest

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.MockEngineConfig
import io.ktor.client.engine.mock.MockRequestHandler
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Test HTTP engine that replaces the production engine through Hilt.
 *
 * Use this when the HTTP engine is provided by Hilt.
 */
@Singleton
class TestNetworkEngine @Inject constructor() :
    HttpClientEngineFactory<MockEngineConfig> {

    private val config = MockEngineConfig().apply {
        reuseHandlers = false
    }

    val engine = MockEngine.Queue(config)

    override fun create(
        block: MockEngineConfig.() -> Unit
    ): HttpClientEngine {
        config.apply(block)
        return engine
    }

    /**
     * Enqueues a handler for the next request.
     */
    fun enqueue(handler: MockRequestHandler) {
        engine.enqueue(handler)
    }
}
