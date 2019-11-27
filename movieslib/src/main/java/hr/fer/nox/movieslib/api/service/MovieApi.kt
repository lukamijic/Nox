package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMoviesList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("3/movie/{movie_id}?language=en-US&append_to_response=videos%2Ccredits")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<ApiMovieDetails>

    @GET("3/movie/upcoming?language=en-US")
    fun getPopularMovies(): Single<ApiMoviesList>

    @GET("3/movie/now_playing?language=en-US")
    fun getNewReleasesMovies(): Single<ApiMoviesList>

    @GET("3/movie/upcoming?language=en-US")
    fun getUpcomingMovies(): Single<ApiMoviesList>
}