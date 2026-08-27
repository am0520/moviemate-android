package com.amahmouddm.moviemate.data.movies.datasource.network.client

@ConsistentCopyVisibility
data class ServerConfig private constructor(
    val baseUrl: String,
    val accessToken: String,
) {
    companion object {
        fun create(
            baseUrl: String,
            accessToken: String,
        ): ServerConfig {
            return ServerConfig(
                baseUrl = baseUrl.trimEnd('/') + "/",
                accessToken = accessToken,
            )
        }
    }
}
