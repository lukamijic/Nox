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
import hr.fer.nox.movieslib.usecase.QueryMovieDetails
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val MoviesLibModule = module {

    single(named(BASE_IMAGE_URL_KEY)) { "https://image.tmdb.org/t/p/w300/" }

    single {
        get<Retrofit>().create(MovieApi::class.java)
    }

    single<MovieService> {
        MovieServiceImpl(get())
    }


    single<MovieDetailsMapper> {
        MovieDetailsMapperImpl(get(named(BASE_IMAGE_URL_KEY)))
    }

    single<MovieMapper> {
        MovieMapperImpl(get(named(BASE_IMAGE_URL_KEY)))
    }

    single<MovieSource> { MovieSourceImpl(get(), get(), get()) }

    single { QueryMovieDetails(get()) }
}

private const val BASE_IMAGE_URL_KEY = "BASE_IMAGE_URL"