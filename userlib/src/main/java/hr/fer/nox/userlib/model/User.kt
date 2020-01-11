package hr.fer.nox.userlib.model

data class User(
    val id: String = "",
    val name: String? = "",
    val surname: String? = "",
    val email: String = ""
)