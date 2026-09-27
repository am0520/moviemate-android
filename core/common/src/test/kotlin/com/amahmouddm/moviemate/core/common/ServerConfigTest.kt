package com.amahmouddm.moviemate.core.common

import kotlin.test.Test
import kotlin.test.assertEquals

class ServerConfigTest {

    @Test
    fun `adds trailing slash to base url`() {
        val config = ServerConfig.create(
            baseUrl = "https://example.com",
            accessToken = "token",
        )

        assertEquals(
            expected = "https://example.com/",
            actual = config.baseUrl,
        )
    }

    @Test
    fun `does not duplicate trailing slash`() {
        val config = ServerConfig.create(
            baseUrl = "https://example.com/",
            accessToken = "token",
        )

        assertEquals(
            expected = "https://example.com/",
            actual = config.baseUrl,
        )
    }
}
