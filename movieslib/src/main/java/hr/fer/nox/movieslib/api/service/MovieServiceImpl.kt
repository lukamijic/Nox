package hr.fer.nox.movieslib.api.service

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.api.models.ApiMovieShort
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class MovieServiceImpl(
    private val movieApi: MovieApi
): MovieService {

    override fun getMovieDetails(movieId: Int): Flowable<ApiMovieDetails> = movieApi.getMovieDetails(movieId).toFlowable()

    override fun getPopularMovies(): Flowable<List<ApiMovieShort>> = movieApi.getPopularMovies().toFlowable()

    override fun getNewReleasesMovies(): Flowable<List<ApiMovieShort>> = movieApi.getNewReleasesMovies().toFlowable()

    override fun getUpcomingMovies(): Flowable<List<ApiMovieShort>> = movieApi.getUpcomingMovies().toFlowable()

    override fun getRecommendedMovies(): Flowable<List<ApiMovieShort>> = movieApi.getRecommendedMovies().toFlowable()

    override fun getWeatherRecommendation(lat: Float, long: Float): Flowable<List<ApiMovieShort>> = movieApi.getWeatherRecommendation(lat, long).toFlowable()

    override fun searchMovies(searchTerm: String): Single<List<ApiMovieShort>> = movieApi.searchMovies(searchTerm)

    override fun getMyLikedMovies(): Flowable<List<ApiMovieShort>> = movieApi.getMyLikedMovies().toFlowable()

    override fun getLikedMovies(userId: String): Flowable<List<ApiMovieShort>> = movieApi.getLikedMovies(userId).toFlowable()

    override fun likeMovie(movieId: Int): Completable = movieApi.likeMovie(movieId)

    override fun unlikeMovie(movieId: Int): Completable = movieApi.unlikeMovie(movieId)
}