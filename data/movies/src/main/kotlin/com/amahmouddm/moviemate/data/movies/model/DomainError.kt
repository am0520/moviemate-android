package com.amahmouddm.moviemate.data.movies.model

enum class DomainError {
    ACCESS_DENIED,
    NOT_FOUND,
    RATE_LIMITED,
    REQUEST_TIMED_OUT,
    INVALID_REQUEST,
    INVALID_DATA,
    CONNECTION_FAILED,
    SERVICE_UNAVAILABLE,
    UNKNOWN,
}
