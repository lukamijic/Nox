package hr.fer.nox.userlib.model


data class UserDetails(
    val id: String,
    val name: String,
    val surname: String,
    val email: String,
    val gender: String,
    val age: Int,
    val iq: Int?
)