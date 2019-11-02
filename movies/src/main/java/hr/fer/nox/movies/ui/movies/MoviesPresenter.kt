package hr.fer.nox.movies.ui.movies

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movies.resources.MoviesResources
import hr.fer.nox.navigation.router.Router

class MoviesPresenter(
    private val moviesResources: MoviesResources
): BasePresenter<MoviesContract.View, MoviesViewState>(), MoviesContract.Presenter {

    override fun initialViewState(): MoviesViewState = MoviesViewState(DEFAULT())

    override fun showMovieDetails(movieId: String) {
        dispatchRoutingAction { router -> router.showMovieDetails(movieId)  }
    }
}