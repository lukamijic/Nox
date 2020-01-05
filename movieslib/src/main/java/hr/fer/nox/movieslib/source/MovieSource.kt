package hr.fer.nox.movieslib.source

import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.model.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable

interface MovieSource {

    fun getMovieDetails(movieId: Int): Flowable<MovieDetails>

    fun getPopularMovies(): Flowable<List<Movie>>

    fun getNewReleasesMovies(): Flowable<List<Movie>>

    fun getUpcomingMovies(): Flowable<List<Movie>>

    fun getWeatherRecommendation(lat: Float, long: Float): Flowable<List<Movie>>

    fun querySearchMovies(): Flowable<List<Movie>>

    fun searchMovies(searchTerm: String): Completable
}