package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/movies/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int): Single<ApiMovieDetails>

    @GET("/movies/popular")
    fun getPopularMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/new-releases")
    fun getNewReleasesMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/upcoming")
    fun getUpcomingMovies(): Single<List<ApiMovieShort>>

    @GET("/movies/search")
    fun searchMovies(@Query("query") searchTerm: String): Single<List<ApiMovieShort>>
}