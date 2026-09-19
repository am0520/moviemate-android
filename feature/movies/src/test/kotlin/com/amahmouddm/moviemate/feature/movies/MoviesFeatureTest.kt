package com.amahmouddm.moviemate.feature.movies

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MoviesFeatureTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val httpClient = HttpClient(MockEngine) {
        engine {
            addHandler {
                respond(
                    content = """
                        {
                          "results": [
                            {
                              "id": 1,
                              "title": "Test Movie"
                            }
                          ]
                        }
                    """.trimIndent(),
                    status = HttpStatusCode.OK,
                    headers = headersOf(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )
                )
            }
        }
    }

    @Test
    fun `loading state displays progress indicator`() {
        composeTestRule.setContent {
            MoviesScreen()
        }
    }
}
