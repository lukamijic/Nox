package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMoviesList
import io.reactivex.Flowable
import io.reactivex.Single

class MovieServiceImpl(private val movieApi: MovieApi): MovieService {

    override fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails> =
        movieApi.getMovieDetails(movieId).toFlowable()

    override fun getPopularMovies(): Flowable<ApiMoviesList> =
        movieApi.getPopularMovies().toFlowable()

    override fun getNewReleasesMovies(): Flowable<ApiMoviesList> =
        movieApi.getNewReleasesMovies().toFlowable()

    override fun getUpcomingMovies(): Flowable<ApiMoviesList> =
        movieApi.getUpcomingMovies().toFlowable()

    override fun searchMovies(searchTerm: String): Single<ApiMoviesList> =
        movieApi.searchMovies(searchTerm)
}