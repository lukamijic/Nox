package hr.fer.nox.userlib.model

data class AccessToken(
    val token: String
) {

    companion object {

        val EMPTY = AccessToken("")
    }
}