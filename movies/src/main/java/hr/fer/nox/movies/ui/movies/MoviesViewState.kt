package hr.fer.nox.movies.ui.movies

import hr.fer.nox.movies.model.MovieItemViewModel

data class MoviesViewState(
    var moviesTemplateItemViewModel: List<MovieItemViewModel>,
    var isLoading: Boolean
)
