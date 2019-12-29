package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Flowable
import io.reactivex.Single

class MovieServiceImpl(
    private val movieApi: MovieApi
): MovieService {

    override fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails> = movieApi.getMovieDetails(movieId).toFlowable()

    override fun getPopularMovies(): Flowable<List<ApiMovieShort>> = movieApi.getPopularMovies().toFlowable()

    override fun getNewReleasesMovies(): Flowable<List<ApiMovieShort>> = movieApi.getNewReleasesMovies().toFlowable()

    override fun getUpcomingMovies(): Flowable<List<ApiMovieShort>> = movieApi.getUpcomingMovies().toFlowable()

    override fun searchMovies(searchTerm: String): Single<List<ApiMovieShort>> = movieApi.searchMovies(searchTerm)
}