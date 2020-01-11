package hr.fer.nox.navigation.router

import hr.fer.nox.navigation.model.UserInfo

interface Router {

    fun showSplash()

    fun showLogin()

    fun showCreateAccount()

    fun showHome()

    fun showMovies()

    fun showRecommendations()

    fun showMovieDetails(movieId: Int)

    fun showUserDetails(userInfo: UserInfo)

    fun showSearch()

    fun goBack()
}
