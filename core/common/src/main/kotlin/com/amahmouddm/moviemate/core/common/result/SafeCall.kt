package com.amahmouddm.moviemate.core.common.result

import kotlin.coroutines.cancellation.CancellationException

/**
 * Executes a suspending [call] and wraps its result in an [Outcome].
 *
 * Successful results are returned as [Outcome.Success]. If the call throws an
 * [Exception], it is converted using [mapError] and returned as [Outcome.Failure].
 *
 * @param mapError Converts an exception from [call] into a failure value.
 * @param call The suspending operation to execute.
 */
suspend fun <T, E> safeCall(
    mapError: (Exception) -> E,
    call: suspend () -> T,
): Outcome<T, E> {
    return try {
        Outcome.Success(call())
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Outcome.Failure(mapError(exception))
    }
}
