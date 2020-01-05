package hr.fer.nox.movieslib.di

import hr.fer.nox.movieslib.api.service.MovieApi
import hr.fer.nox.movieslib.api.service.MovieService
import hr.fer.nox.movieslib.api.service.MovieServiceImpl
import hr.fer.nox.movieslib.mapper.MovieDetailsMapper
import hr.fer.nox.movieslib.mapper.MovieDetailsMapperImpl
import hr.fer.nox.movieslib.mapper.MovieMapper
import hr.fer.nox.movieslib.mapper.MovieMapperImpl
import hr.fer.nox.movieslib.source.MovieSource
import hr.fer.nox.movieslib.source.MovieSourceImpl
import hr.fer.nox.movieslib.usecase.*
import org.koin.dsl.module
import retrofit2.Retrofit
import kotlin.math.sin

val MoviesLibModule = module {

    single {
        get<Retrofit>().create(MovieApi::class.java)
    }

    single<MovieService> { MovieServiceImpl(get()) }

    single<MovieDetailsMapper> {
        MovieDetailsMapperImpl()
    }

    single<MovieMapper> {
        MovieMapperImpl()
    }

    single<MovieSource> { MovieSourceImpl(get(), get(), get()) }

    single { QueryMovieDetails(get()) }

    single { QuerySearchMovies(get()) }

    single { SearchMovies(get()) }

    single { QueryIsMyMovieLiked(get()) }

    single { QueryLikedMovies(get()) }

    single { QueryMyLikedMovies(get()) }

    single { LikeMovie(get()) }

    single { UnlikeMovie(get()) }
}