package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Completable

class SearchMovies(
    private val movieSource: MovieSource
) : CommandUseCaseWithParameter<String> {

    override fun invoke(parameter: String): Completable = movieSource.searchMovies(parameter)
}