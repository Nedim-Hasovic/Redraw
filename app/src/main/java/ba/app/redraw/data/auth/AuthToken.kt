package ba.app.redraw.data.auth

import java.util.Calendar
import java.util.TimeZone

/**
 * Interface representing a simple authentication token that can be added to calls
 */
interface AuthToken {
    val stringValue: String
}

/**
 * Basic implementation of [AuthToken]
 */
open class BasicAuthToken(override val stringValue: String) : AuthToken

/**
 * Token that can be refreshed
 */
interface RefreshableAuthToken : AuthToken {
    val refreshTokenStringValue: String
}

/**
 * Token that can expire
 */
interface ExpirableAuthToken : AuthToken {
    fun expired(): Boolean
}

/**
 * Token that expires at a specific time
 */
interface SetTimeExpirableAuthToken : ExpirableAuthToken {
    val expirationTimestamp: Long

    override fun expired(): Boolean {
        val currentTime = Calendar.getInstance(TimeZone.getTimeZone("UTC")).timeInMillis / 1000
        return currentTime >= expirationTimestamp
    }
}

/**
 * Token that can be refreshed and expires at a set time (like JWT and OAuth2)
 */
interface RefreshableSetTimeExpirableAuthToken : SetTimeExpirableAuthToken, RefreshableAuthToken
