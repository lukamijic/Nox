package hr.fer.nox.userdetails.ui


data class UserDetailsViewState(
    var userId: String,
    var name: String,
    var surname: String,
    var email: String,
    var age: Int?,
    var gender: String,
    var isLoading: Boolean
) {

    companion object {

        val EMPTY = UserDetailsViewState(
           "",
            "",
            "",
            "",
            null,
            "",
            false
            )

        val NOT_EMPTY = UserDetailsViewState(
            "userId",
            "Karlo",
            "Razumovic",
            "karlo.razumovic@gmail.com",
            25,
            "Male",
                false
        )
    }
}