package com.amahmouddm.moviemate.core.common.result

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertSame

class OutcomeMapSuccessTest {

    @Test
    fun `transforms success data`() {
        // Given
        val outcome: Outcome<Int, String> = Outcome.Success(2)

        // When
        val result = outcome.mapSuccess { value ->
            value * 3
        }

        // Then
        assertEquals(
            expected = Outcome.Success(6),
            actual = result,
        )
    }

    @Test
    fun `returns failure unchanged`() {
        // Given
        val failure: Outcome<Int, String> = Outcome.Failure("Network error")

        // When
        val result = failure.mapSuccess { value ->
            value * 3
        }

        // Then
        assertSame(
            expected = failure,
            actual = result,
        )
    }

    @Test
    fun `does not invoke transform for failure`() {
        // Given
        val failure: Outcome<Int, String> = Outcome.Failure("Network error")

        var transformInvoked = false

        // When
        failure.mapSuccess { value ->
            transformInvoked = true
            value * 3
        }

        // Then
        assertFalse(transformInvoked)
    }
}
