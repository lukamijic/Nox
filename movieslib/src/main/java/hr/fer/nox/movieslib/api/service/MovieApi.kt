package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface MovieApi {

    @GET("/movies/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int): Single<ApiMovieDetails>

    @GET("/movies/popular")
    fun getPopularMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/new-releases")
    fun getNewReleasesMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/upcoming")
    fun getUpcomingMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/recommended")
    fun getRecommendedMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/weather")
    fun getWeatherRecommendation(@Query("latitude") lat: Float, @Query("longitude") long: Float): Single<List<ApiMovieShort>>

    @GET("/movies/search")
    fun searchMovies(@Query("query") searchTerm: String): Single<List<ApiMovieShort>>

    @GET("/like/movies")
    fun getMyLikedMovies(): Single<List<ApiMovieShort>>

    @GET("/like/movies/{userId}")
    fun getLikedMovies(@Path("userId") userId: String): Single<List<ApiMovieShort>>

    @POST("/like/{movieId}")
    fun likeMovie(@Path("movieId") movieId: Int): Completable

    @DELETE("/like/{movieId}")
    fun unlikeMovie(@Path("movieId") movieId: Int): Completable
}