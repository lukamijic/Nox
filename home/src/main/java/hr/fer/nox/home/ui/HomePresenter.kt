package hr.fer.nox.home.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.navigation.router.Router

class HomePresenter: BasePresenter<HomeContract.View, HomeViewState>(), HomeContract.Presenter {

    override fun initialViewState(): HomeViewState = HomeViewState()

    override fun showMovies() {
        dispatchRoutingAction(Router::showMovies)
    }

    override fun showExplore() {
    }

    override fun showRecommendations() {
    }

    override fun showProfile() {
    }
}