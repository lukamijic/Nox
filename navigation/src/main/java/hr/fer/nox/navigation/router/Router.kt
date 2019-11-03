package hr.fer.nox.navigation.router

interface Router {

    fun showSplash()

    fun showLogin()

    fun showCreateAccount()

    fun showHome()

    fun showMovies()

    fun showMovieDetails(movieId: String)

    fun goBack()
}