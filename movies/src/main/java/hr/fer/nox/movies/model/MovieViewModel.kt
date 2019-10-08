package hr.fer.nox.movies.model

import androidx.annotation.DrawableRes
import hr.fer.nox.coreui.util.DiffUtilViewModel


data class MovieItemViewModel(
    @DrawableRes val moviePosterResId: Int
): DiffUtilViewModel()