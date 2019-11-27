package hr.fer.nox.movieslib.mapper

import hr.fer.nox.movieslib.api.models.ApiMovieShort
import hr.fer.nox.movieslib.model.Movie

class MovieMapperImpl(
    private val baseImageUrl: String
): MovieMapper {

    override fun map(apiMovieShort: ApiMovieShort): Movie =
        Movie(
            apiMovieShort.id,
            apiMovieShort.movieTitle,
            apiMovieShort.posterPath?.run { baseImageUrl + this }
        )

    override fun map(apiMoviesShort: List<ApiMovieShort>): List<Movie> =
        apiMoviesShort.map(this::map)
}