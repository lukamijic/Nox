package hr.fer.nox.movieslib.mapper

import hr.fer.nox.movieslib.api.models.ApiMovieShort
import hr.fer.nox.movieslib.model.Movie

interface MovieMapper {

    fun map(apiMovieShort: ApiMovieShort): Movie

    fun map(apiMoviesShort: List<ApiMovieShort>): List<Movie>
}