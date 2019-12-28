package hr.fer.nox.userlib.preferences

import android.content.SharedPreferences
import hr.fer.nox.userlib.model.AccessToken

class UserPreferencesImpl(
    private val sharedPreferences: SharedPreferences
): UserPreferences {

    companion object {

        private const val KEY_TOKEN = "token"
    }

    override fun getAccessToken(): AccessToken {
        val token: String? = sharedPreferences.getString(KEY_TOKEN, null)
        return if (token == null) {
            AccessToken.EMPTY
        } else {
            AccessToken(token)
        }
    }

    override fun setAccessToken(accessToken: String?) =
        sharedPreferences
            .edit()
            .putString(KEY_TOKEN, accessToken)
            .apply()
}