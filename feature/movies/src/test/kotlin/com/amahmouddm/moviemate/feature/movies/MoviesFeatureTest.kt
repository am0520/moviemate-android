package com.amahmouddm.moviemate.feature.movies

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.amahmouddm.moviemate.core.datatest.FakeServer
import com.amahmouddm.moviemate.core.screenshottesting.captureScreenshot
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import javax.inject.Inject
import kotlin.test.Test

@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class MoviesFeatureTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule =
        createAndroidComposeRule<HiltTestActivity>()

    @Inject
    lateinit var fakeServer: FakeServer

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun displaysMoviesReturnedByServer() {
        fakeServer.enqueueResponse(
            body = """
                {
                    "results": [
                        { "id": 1, "title": "Alien" },
                        { "id": 2, "title": "Arrival" }
                    ]
                }
            """.trimIndent()
        )

        composeTestRule.setContent {
            MoviesScreen()
        }

        composeTestRule
            .onNodeWithText("Alien")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("Arrival")
            .assertIsDisplayed()

        composeTestRule.captureScreenshot(
            categories = listOf("MoviesFeature", "success"),
        )
    }

}
