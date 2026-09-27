package com.amahmouddm.moviemate.feature.movies

import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasProgressBarRangeInfo
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amahmouddm.moviemate.core.networktest.TestNetworkEngine
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.ktor.client.engine.mock.respond
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.CompletableDeferred
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.Test

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class MoviesFeatureTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule =
        createAndroidComposeRule<HiltTestActivity>()

    @Inject
    lateinit var testNetworkEngine: TestNetworkEngine

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun `displays loading while movies are being fetched`() {
        val responseGate = CompletableDeferred<Unit>()

        try {
            testNetworkEngine.enqueue {
                responseGate.await()

                respond(
                    content = """
                    {
                        "results": [
                            { "id": 1, "title": "Alien" }
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

            composeTestRule.setContent {
                MoviesScreen()
            }

            composeTestRule
                .onNode(
                    hasProgressBarRangeInfo(
                        ProgressBarRangeInfo.Indeterminate
                    )
                )
                .assertIsDisplayed()
        } finally {
            responseGate.complete(Unit)
        }
    }

    @Test
    fun `displays movies returned by server`() {
        testNetworkEngine.enqueue {
            respond(
                content = """
                {
                    "results": [
                        { "id": 1, "title": "Alien" },
                        { "id": 2, "title": "Arrival" }
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

        composeTestRule.setContent {
            MoviesScreen()
        }

        composeTestRule
            .onNodeWithText("Alien")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Arrival")
            .assertIsDisplayed()
    }

    @Test
    fun `displays error when server returns an error`() {
        testNetworkEngine.enqueue {
            respond(
                content = "",
                status = HttpStatusCode.InternalServerError
            )
        }

        composeTestRule.setContent {
            MoviesScreen()
        }

        composeTestRule
            .onNodeWithText("Error")
            .assertIsDisplayed()
    }
}
