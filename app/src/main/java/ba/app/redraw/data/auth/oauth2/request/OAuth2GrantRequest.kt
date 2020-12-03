package ba.app.redraw.data.auth.oauth2.request

import com.google.gson.annotations.SerializedName

/**

 */
abstract class OAuth2GrantRequest(
    @field:SerializedName("grant_type")
    private val grantType: String,
    @field:SerializedName("client_id")
    private val clientId: String,
    @field:SerializedName("client_secret")
    private val clientSecret: String
)
