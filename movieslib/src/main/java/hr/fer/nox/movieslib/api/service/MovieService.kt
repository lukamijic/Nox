package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface MovieService {

    fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails>

    fun getPopularMovies(): Flowable<List<ApiMovieShort>>

    fun getNewReleasesMovies(): Flowable<List<ApiMovieShort>>

    fun getUpcomingMovies(): Flowable<List<ApiMovieShort>>

    fun getRecommendedMovies(): Flowable<List<ApiMovieShort>>

    fun getWeatherRecommendation(lat: Float, long: Float): Flowable<List<ApiMovieShort>>

    fun searchMovies(searchTerm: String): Single<List<ApiMovieShort>>

    fun getMyLikedMovies(): Flowable<List<ApiMovieShort>>

    fun getLikedMovies(userId: String): Flowable<List<ApiMovieShort>>

    fun likeMovie(movieId: Int): Completable

    fun unlikeMovie(movieId: Int): Completable
}