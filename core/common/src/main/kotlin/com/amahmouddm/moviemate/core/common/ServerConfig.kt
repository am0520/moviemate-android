package com.amahmouddm.moviemate.core.common

import com.amahmouddm.moviemate.core.common.ServerConfig.Companion.create

/**
 * Configuration required to connect to a server.
 *
 * Instances should be created using [create], which normalizes [baseUrl]
 * by ensuring it ends with a trailing slash.
 *
 * @property baseUrl The normalized base URL of the server.
 * @property accessToken The access token used to authenticate requests.
 */
@ConsistentCopyVisibility
data class ServerConfig private constructor(
    val baseUrl: String,
    val accessToken: String,
) {
    companion object {
        /**
         * Creates a [ServerConfig].
         *
         * The provided [baseUrl] is normalized to contain exactly one
         * trailing slash.
         *
         * @param baseUrl The server's base URL.
         * @param accessToken The token used for authentication.
         */
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
