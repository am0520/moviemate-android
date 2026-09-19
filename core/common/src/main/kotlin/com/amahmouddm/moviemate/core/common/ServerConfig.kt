package com.amahmouddm.moviemate.core.common

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
