package ba.app.redraw.data.auth.oauth2.request

import com.google.gson.annotations.SerializedName

/**

 */
class OAuth2CreateTokenRequest(
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("password")
    val password: String,
    clientId: String,
    clientSecret: String
) : OAuth2GrantRequest(GRANT_TYPE, clientId, clientSecret) {
    companion object {
        private const val GRANT_TYPE = "password"
    }
}
