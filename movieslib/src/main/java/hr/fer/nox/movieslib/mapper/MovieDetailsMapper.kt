package hr.fer.nox.movieslib.mapper

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.model.MovieDetails

interface MovieDetailsMapper {

    fun map(apiMovieDetails: ApiMovieDetails): MovieDetails
}