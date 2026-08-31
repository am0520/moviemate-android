package com.amahmouddm.moviemate.data.movies.testdata

import com.amahmouddm.moviemate.data.movies.model.DomainError

val domainErrorTestData = listOf(
    DomainError.ACCESS_DENIED,
    DomainError.NOT_FOUND,
    DomainError.RATE_LIMITED,
    DomainError.REQUEST_TIMED_OUT,
    DomainError.INVALID_REQUEST,
    DomainError.INVALID_DATA,
    DomainError.CONNECTION_FAILED,
    DomainError.SERVICE_UNAVAILABLE,
    DomainError.UNKNOWN,
)
