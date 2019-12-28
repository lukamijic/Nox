package hr.fer.nox.movies.usecase.movies

import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryNewReleasesMovies(
    private val movieSource: MovieSource
): QueryMovieList {

    override fun invoke(): Flowable<List<Movie>> = movieSource.getNewReleasesMovies()
}