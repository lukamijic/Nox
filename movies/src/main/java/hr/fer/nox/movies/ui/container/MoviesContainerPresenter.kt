package hr.fer.nox.movies.ui.container

import hr.fer.nox.coreui.base.BasePresenter

class MoviesContainerPresenter: BasePresenter<MoviesContainerContract.View, MoviesContainerViewState>(), MoviesContainerContract.Presenter {

    override fun initialViewState(): MoviesContainerViewState = MoviesContainerViewState()
}