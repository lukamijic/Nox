package hr.fer.nox.movies.ui.movies

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movies.resources.MoviesResources

class MoviesPresenter(
    private val moviesResources: MoviesResources
): BasePresenter<MoviesContract.View, MoviesViewState>(), MoviesContract.Presenter {

    override fun initialViewState(): MoviesViewState = MoviesViewState(moviesResources.getTitle())
}