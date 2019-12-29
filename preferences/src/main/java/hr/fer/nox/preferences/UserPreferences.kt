package hr.fer.nox.preferences

interface UserPreferences {

    fun getAccessToken(): AccessToken

    fun setAccessToken(accessToken: String?)
}