package hr.fer.nox.home.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.navigation.router.Router

class HomePresenter: BasePresenter<HomeContract.View, HomeViewState>(), HomeContract.Presenter {

    override fun initialViewState(): HomeViewState = HomeViewState()

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
}