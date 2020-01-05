package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Completable

class LikeMovie(
    private val movieSource: MovieSource
): CommandUseCaseWithParameter<Int> {

    override fun invoke(parameter: Int): Completable = movieSource.likeMovie(parameter)
}