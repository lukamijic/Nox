package hr.fer.nox.movieslib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import io.reactivex.Flowable

class QueryMyLikedMovies(
    private val moviesSource: MovieSource
): QueryUseCase<List<Movie>> {

    override fun invoke(): Flowable<List<Movie>> = moviesSource.getMyLikedMovies()
}