package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.QueryUseCaseWithParameter
import hr.fer.nox.movieslib.model.MovieDetails
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryMovieDetails(
    private val movieSource: MovieSource
): QueryUseCaseWithParameter<Int, MovieDetails> {

    override fun invoke(param: Int): Flowable<MovieDetails> = movieSource.getMovieDetails(param)
}