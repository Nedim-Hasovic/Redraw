package ba.app.redraw.data.auth.oauth2.request

import ba.app.redraw.data.auth.ClientConfig
import javax.inject.Inject

/**
 * Provides easy creation of [OAuth2GrantRequest] objects

 */
class OAuth2RequestFactory
@Inject constructor(private val clientConfig: ClientConfig) {

    fun makeCreateTokenRequest(
        username: String,
        password: String
    ): OAuth2CreateTokenRequest {
        return OAuth2CreateTokenRequest(
            username,
            password,
            clientConfig.clientId,
            clientConfig.clientSecret
        )
    }

    fun makeRefreshTokenRequest(refreshToken: String): OAuth2RefreshTokenRequest {
        return OAuth2RefreshTokenRequest(
            refreshToken,
            clientConfig.clientId,
            clientConfig.clientSecret
        )
    }
}
