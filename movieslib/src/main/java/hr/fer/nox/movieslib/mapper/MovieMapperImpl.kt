package hr.fer.nox.movieslib.mapper

import hr.fer.nox.movieslib.api.models.ApiMovieShort
import hr.fer.nox.movieslib.model.Movie

class MovieMapperImpl: MovieMapper {

    override fun map(apiMovieShort: ApiMovieShort): Movie =
        Movie(
            apiMovieShort.id,
            apiMovieShort.posterPath?.replace("original", "w300")
        )

    override fun map(apiMoviesShort: List<ApiMovieShort>): List<Movie> =
        apiMoviesShort.map(this::map)
}