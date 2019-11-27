package hr.fer.nox.movies.ui.movies

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movies.model.MovieItemViewModel
import hr.fer.nox.movies.resources.MoviesResources
import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.navigation.router.Router

class MoviesPresenter(
    private val queryMovieList: QueryMovieList,
    private val moviesResources: MoviesResources
): BasePresenter<MoviesContract.View, MoviesViewState>(), MoviesContract.Presenter {

    override fun initialViewState(): MoviesViewState = MoviesViewState(emptyList(), true)

    override fun onStart() {
        query(
            queryMovieList()
                .map(this::toViewStateAction)
        )
    }

    override fun showMovieDetails(movieId: Int) {
        dispatchRoutingAction { router -> router.showMovieDetails(movieId)  }
    }

    private fun toViewStateAction(movies: List<Movie>): (MoviesViewState) -> Unit = {
        it.moviesTemplateItemViewModel = movies.map { movie -> MovieItemViewModel(movie.id, movie.moviePosterPath) }
        it.isLoading = false
    }
}