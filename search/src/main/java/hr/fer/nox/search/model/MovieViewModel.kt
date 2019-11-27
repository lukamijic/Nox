package hr.fer.nox.search.model

import hr.fer.nox.coreui.util.DiffUtilViewModel

data class MovieItemViewModel(
    val movieId: Int,
    val moviePosterPath: String?
): DiffUtilViewModel(movieId)