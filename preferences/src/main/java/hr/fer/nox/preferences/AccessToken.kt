package hr.fer.nox.preferences

data class AccessToken(
    val token: String
) {

    companion object {

        val EMPTY = AccessToken("")
    }
}