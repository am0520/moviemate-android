package com.amahmouddm.moviemate.core.common.result

import kotlin.coroutines.cancellation.CancellationException

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
