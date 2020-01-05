package hr.fer.nox.movieslib.source

import hr.fer.nox.movieslib.api.service.MovieService
import hr.fer.nox.movieslib.mapper.MovieDetailsMapper
import hr.fer.nox.movieslib.mapper.MovieMapper
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.model.MovieDetails
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor

class MovieSourceImpl(
    private val movieService: MovieService,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieMapper: MovieMapper
): MovieSource {

    private val searchMoviesPublishProcessor: PublishProcessor<List<Movie>> = PublishProcessor.create()

    override fun getMovieDetails(movieId: Int): Flowable<MovieDetails> = movieService.getMovieDetails(movieId).map { movieDetailsMapper.map(it) }

    override fun getPopularMovies(): Flowable<List<Movie>> = movieService.getPopularMovies().map { movieMapper.map(it) }

    override fun getNewReleasesMovies(): Flowable<List<Movie>> = movieService.getNewReleasesMovies().map { movieMapper.map(it) }

    override fun getUpcomingMovies(): Flowable<List<Movie>> = movieService.getUpcomingMovies().map { movieMapper.map(it) }

    override fun getWeatherRecommendation(lat: Float, long: Float): Flowable<List<Movie>> = getNewReleasesMovies()

    override fun querySearchMovies(): Flowable<List<Movie>> = searchMoviesPublishProcessor

    override fun searchMovies(searchTerm: String): Completable =
        movieService
            .searchMovies(searchTerm)
            .map { movieMapper.map(it) }
            .flatMapCompletable { movies -> Completable.fromAction { searchMoviesPublishProcessor.onNext(movies) }}
}