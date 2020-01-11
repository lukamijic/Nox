package hr.fer.nox.userlib.model

data class LoggedUserDetails(
    val userDetails: UserDetails,
    val likedUsers: List<User>
)