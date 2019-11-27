package hr.fer.nox.search.ui.movies

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.usecase.QuerySearchMovies
import hr.fer.nox.movieslib.usecase.SearchMovies
import hr.fer.nox.search.model.MovieItemViewModel

class SearchMoviesPresenter(
    private val querySearchMovies: QuerySearchMovies,
    private val searchMovies: SearchMovies
) : BasePresenter<SearchMoviesContract.View, SearchMoviesViewState>(), SearchMoviesContract.Presenter {

    override fun initialViewState(): SearchMoviesViewState = SearchMoviesViewState(emptyList(), false)

    override fun onStart() {
        query(
            querySearchMovies()
                .map(this::toViewStateAction)
        )
    }

    override fun searchMovies(searchTerm: String) {
        if (searchTerm.length >= 3) {
            mutateViewState { it.isLoading = true }
            runCommand(searchMovies.invoke(searchTerm))
        } else if (searchTerm.isEmpty()) {
            mutateViewState {
                it.isLoading = false
                it.movies = emptyList()
            }
        }

    }

    override fun showMovieDetails(movieId: Int) {
        dispatchRoutingAction { router -> router.showMovieDetails(movieId)  }
    }

    private fun toViewStateAction(movies: List<Movie>): (SearchMoviesViewState) -> Unit = {
        it.isLoading = false
        it.movies = movies.map { movie -> MovieItemViewModel(movie.id, movie.moviePosterPath) }
    }
}