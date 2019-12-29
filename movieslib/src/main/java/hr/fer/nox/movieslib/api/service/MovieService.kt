package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieService {

    fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails>

    fun getPopularMovies(): Flowable<List<ApiMovieShort>>

    fun getNewReleasesMovies(): Flowable<List<ApiMovieShort>>

    fun getUpcomingMovies(): Flowable<List<ApiMovieShort>>

    fun searchMovies(searchTerm: String): Single<List<ApiMovieShort>>
}