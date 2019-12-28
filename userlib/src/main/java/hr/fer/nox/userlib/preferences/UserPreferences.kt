package hr.fer.nox.userlib.preferences

import hr.fer.nox.userlib.model.AccessToken

interface UserPreferences {

    fun getAccessToken(): AccessToken

    fun setAccessToken(accessToken: String?)
}