package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.QueryUseCaseWithParameter
import io.reactivex.Flowable

class QueryIsMyMovieLiked(
    private val queryMyLikedMovies: QueryMyLikedMovies
): QueryUseCaseWithParameter<Int, Boolean> {

    override fun invoke(param: Int): Flowable<Boolean> =
        queryMyLikedMovies()
            .map { it.map { movie -> movie.id }.contains(param) }
}