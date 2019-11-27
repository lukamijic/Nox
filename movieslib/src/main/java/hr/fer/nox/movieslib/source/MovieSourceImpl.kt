package hr.fer.nox.movieslib.source

import hr.fer.nox.movieslib.api.service.MovieService
import hr.fer.nox.movieslib.mapper.MovieDetailsMapper
import hr.fer.nox.movieslib.mapper.MovieMapper
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.model.MovieDetails
import io.reactivex.Flowable

class MovieSourceImpl(
    private val movieService: MovieService,
    private val movieDetailsMapper: MovieDetailsMapper,
    private val movieMapper: MovieMapper
): MovieSource {

    override fun getMovieDetails(movieId: Int): Flowable<MovieDetails> = movieService.getMovieDetails(movieId).map { movieDetailsMapper.map(it, true) }

    override fun getPopularMovies(): Flowable<List<Movie>> = movieService.getPopularMovies().map { movieMapper.map(it.results) }

    override fun getNewReleasesMovies(): Flowable<List<Movie>> = movieService.getNewReleasesMovies().map { movieMapper.map(it.results) }

    override fun getUpcomingMovies(): Flowable<List<Movie>> = movieService.getUpcomingMovies().map { movieMapper.map(it.results) }
}