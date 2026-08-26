package com.amahmouddm.moviemate.core.common.result

sealed interface Outcome<out T, out E> {

    data class Success<T>(
        val data: T,
    ) : Outcome<T, Nothing>

    data class Failure<E>(
        val error: E,
    ) : Outcome<Nothing, E>
}

inline fun <D, E, R> Outcome<D, E>.mapSuccess(
    transform: (D) -> R,
): Outcome<R, E> {
    return when (this) {
        is Outcome.Success -> Outcome.Success(transform(data))
        is Outcome.Failure -> this
    }
}
