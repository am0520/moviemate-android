package com.amahmouddm.moviemate.core.common.result

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertSame

class SafeCallTest {

    @Test
    fun `returns success when call succeeds`() = runTest {
        // Given
        val expected = "Hello"

        // When
        val result = safeCall(
            mapError = { "" }
        ) {
            expected
        }

        // Then
        assertEquals(
            expected = Outcome.Success(expected),
            actual = result,
        )
    }

    @Test
    fun `returns failure when call throws exception`() = runTest {
        // Given
        val exception = IllegalStateException()

        // When
        val result = safeCall<String, String>(
            mapError = { "" }
        ) {
            throw exception
        }

        // Then
        assertIs<Outcome.Failure<String>>(
            value = result,
        )
    }

    @Test
    fun `rethrows cancellation exception`() = runTest {
        // Given
        val exception = CancellationException()

        // When
        val result = assertFailsWith<CancellationException> {
            safeCall<String, String>(
                mapError = { "" }
            ) {
                throw exception
            }
        }

        // Then
        assertSame(
            expected = exception,
            actual = result,
        )
    }
}
