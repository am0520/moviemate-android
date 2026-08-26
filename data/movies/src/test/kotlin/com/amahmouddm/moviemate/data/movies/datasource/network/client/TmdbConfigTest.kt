package com.amahmouddm.moviemate.data.movies.datasource.network.client

import kotlin.test.Test
import kotlin.test.assertEquals

class TmdbConfigTest {

    @Test
    fun `adds trailing slash to base url`() {
        val config = TmdbConfig.create(
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
        val config = TmdbConfig.create(
            baseUrl = "https://example.com/",
            accessToken = "token",
        )

        assertEquals(
            expected = "https://example.com/",
            actual = config.baseUrl,
        )
    }
}
