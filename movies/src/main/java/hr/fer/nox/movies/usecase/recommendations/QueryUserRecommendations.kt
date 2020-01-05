package hr.fer.nox.movies.usecase.recommendations

import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryUserRecommendations(
    private val movieSource: MovieSource
): QueryMovieList {

    override fun invoke(): Flowable<List<Movie>> = movieSource.getRecommendedMovies()
}