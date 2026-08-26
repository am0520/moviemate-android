package com.amahmouddm.moviemate.data.movies.datasource.network.result

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.model.DomainError
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertIs
import kotlin.test.assertSame

class SafeApiCallTest {

    @Test
    fun `returns success when call succeeds`() = runTest {
        // Given
        val expected = "Hello"

        // When
        val result = safeApiCall {
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
        val result = safeApiCall<String> {
            throw exception
        }

        // Then
        assertIs<Outcome.Failure<DomainError>>(
            value = result,
        )
    }

    @Test
    fun `rethrows cancellation exception`() = runTest {
        // Given
        val exception = CancellationException()

        // When
        val result = assertFailsWith<CancellationException> {
            safeApiCall<String> {
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
