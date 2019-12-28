package hr.fer.nox.navigation.router

interface Router {

    fun showSplash()

    fun showLogin()

    fun showCreateAccount()

    fun showHome()

    fun showMovies()

    fun showRecommendations()

    fun showMovieDetails(movieId: Int)

    fun showUserDetails(userId: String, isProfileTab: Boolean)

    fun showSearch()

    fun goBack()
}