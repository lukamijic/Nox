package hr.fer.nox.movies.usecase

import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryPopularMovies(
    private val movieSource: MovieSource
): QueryMovieList {

    override fun invoke(): Flowable<List<Movie>> = movieSource.getPopularMovies()
}