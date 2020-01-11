package hr.fer.nox.navigation.model

data class UserInfo(
    val userId: String
) {

    companion object {

        val ME = UserInfo("ME")
    }
}
