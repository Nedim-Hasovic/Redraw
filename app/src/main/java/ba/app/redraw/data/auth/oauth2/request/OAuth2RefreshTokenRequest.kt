package ba.app.redraw.data.auth.oauth2.request

import com.google.gson.annotations.SerializedName

/**

 */
class OAuth2RefreshTokenRequest(
    @field:SerializedName("refresh_token")
    val refreshToken: String,
    clientId: String,
    clientSecret: String
) : OAuth2GrantRequest(GRANT_TYPE, clientId, clientSecret) {
    companion object {
        private const val GRANT_TYPE = "refresh_token"
    }
}
