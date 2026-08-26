package com.amahmouddm.moviemate.data.movies.datasource.network.result

import com.amahmouddm.moviemate.core.common.result.Outcome
import com.amahmouddm.moviemate.data.movies.datasource.network.mapper.toDomainError
import com.amahmouddm.moviemate.data.movies.model.DomainError
import kotlinx.coroutines.CancellationException

internal suspend fun <T> safeApiCall(
    call: suspend () -> T,
): Outcome<T, DomainError> {
    return try {
        Outcome.Success(call())
    } catch (exception: CancellationException) {
        throw exception
    } catch (exception: Exception) {
        Outcome.Failure(exception.toDomainError())
    }
}
