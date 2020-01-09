package hr.fer.nox.userdetails.model

import hr.fer.nox.coreui.util.DiffUtilViewModel

data class MovieItemViewModel(
    val movieId: Int,
    val moviePosterPath: String?
): DiffUtilViewModel(movieId)