package hr.fer.nox.home.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.userlib.model.LoggedUserDetails
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.userlib.mapper.UserMapper
import hr.fer.nox.userlib.service.UserApi

class HomePresenter(
    private val userApi: UserApi,
    private val userMapper: UserMapper
): BasePresenter<HomeContract.View, HomeViewState>(), HomeContract.Presenter {

    override fun initialViewState(): HomeViewState = HomeViewState()

    override fun onStart() {
        Thread(Runnable {
           loggedUserDetails = getLoggedInUserDetails()
        }).start()
    }

    override fun showMovies() {
        dispatchRoutingAction(Router::showMovies)
    }

    override fun showExplore() {
        dispatchRoutingAction(Router::showSearch)
    }

    override fun showRecommendations() {
        dispatchRoutingAction(Router::showRecommendations)
    }

    override fun showProfile() {
        dispatchRoutingAction { router -> router.showUserDetails("ME", true) }
    }


    private fun getLoggedInUserDetails(): LoggedUserDetails {
        val userDetails = userApi.getMyUserDetails().map{ userMapper.map(it) }.blockingFirst()
        val likedUsers = userApi.getLikedUsers().map{userMapper.map(it)}.blockingFirst()
        return LoggedUserDetails( userDetails, likedUsers)
    }


}