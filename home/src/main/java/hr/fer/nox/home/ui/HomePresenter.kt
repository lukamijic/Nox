package hr.fer.nox.home.ui

import hr.fer.nox.coreui.base.BasePresenter

class HomePresenter(): BasePresenter<HomeContract.View, HomeViewState>(), HomeContract.Presenter {

    override fun initialViewState(): HomeViewState = HomeViewState()
}