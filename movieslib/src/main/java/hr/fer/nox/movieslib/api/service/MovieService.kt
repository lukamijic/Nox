package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMoviesList
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieService {

    fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails>

    fun getPopularMovies(): Flowable<ApiMoviesList>

    fun getNewReleasesMovies(): Flowable<ApiMoviesList>

    fun getUpcomingMovies(): Flowable<ApiMoviesList>

    fun searchMovies(searchTerm: String): Single<ApiMoviesList>
}