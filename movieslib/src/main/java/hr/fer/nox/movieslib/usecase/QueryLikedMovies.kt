package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.QueryUseCaseWithParameter
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryLikedMovies(
    private val movieSource: MovieSource
): QueryUseCaseWithParameter<String, List<Movie>> {

    /**
     * param = userId
     */
    override fun invoke(param: String): Flowable<List<Movie>> = movieSource.getLikedMovies(param)
}