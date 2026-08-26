package com.amahmouddm.moviemate.data.movies.datasource.network.client

@ConsistentCopyVisibility
data class TmdbConfig private constructor(
    val baseUrl: String,
    val accessToken: String,
) {
    companion object {
        fun create(
            baseUrl: String,
            accessToken: String,
        ): TmdbConfig {
            return TmdbConfig(
                baseUrl = baseUrl.trimEnd('/') + "/",
                accessToken = accessToken,
            )
        }
    }
}
