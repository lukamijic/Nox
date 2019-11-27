package hr.fer.nox.search.ui.movies

import hr.fer.nox.search.model.MovieItemViewModel

data class SearchMoviesViewState(
    var movies: List<MovieItemViewModel>,
    var isLoading: Boolean
)