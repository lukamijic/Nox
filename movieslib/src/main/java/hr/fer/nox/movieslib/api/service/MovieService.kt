package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMoviesList
import io.reactivex.Flowable

interface MovieService {

    fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails>

    fun getPopularMovies(): Flowable<ApiMoviesList>

    fun getNewReleasesMovies(): Flowable<ApiMoviesList>

    fun getUpcomingMovies(): Flowable<ApiMoviesList>
}