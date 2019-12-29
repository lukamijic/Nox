package hr.fer.nox.core.di

import android.content.Context
import android.net.ConnectivityManager
import hr.fer.nox.core.BuildConfig
import hr.fer.nox.core.networking.ConnectivityManagerWrapper
import hr.fer.nox.core.networking.ConnectivityManagerWrapperImpl
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NetworkUtilsImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val NetworkingModule = module {

    single(named(API_KEY_IDENTIFIER)) {
        "d2b00cc05927175409fe8e98eb5d0d5c"
    }

    single(named(MOVIE_DATABASE_URL_IDENTIFIER)) {
        "https://api.themoviedb.org/"
    }

    single(named(NOX_BASE_URL_IDENTIFIER)) { "https://nox.herokuapp.com/" }

    single { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single<ConnectivityManagerWrapper> { ConnectivityManagerWrapperImpl(get()) }

    single<NetworkUtils> { NetworkUtilsImpl(androidApplication(), get()) }

    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newUrl = chain.request().url.newBuilder()
                    .addQueryParameter("api_key", get(named(API_KEY_IDENTIFIER)))
                    .build()
                chain.proceed(chain.request().newBuilder().url(newUrl).build())
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    single(named(MOVIE_DATABASE_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(MOVIE_DATABASE_URL_IDENTIFIER)))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

    single(named(NOX_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(get<String>(named(NOX_BASE_URL_IDENTIFIER)))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

}

const val API_KEY_IDENTIFIER = "API_KEY"
const val MOVIE_DATABASE_RETROFIT = "MOVIE_DATABASE_RETROFIT"
const val MOVIE_DATABASE_URL_IDENTIFIER = "MOVIE_DATABASE_URL"
const val NOX_BASE_URL_IDENTIFIER = "NOX_URL"
const val NOX_RETROFIT = "NOX_RETROFIT"