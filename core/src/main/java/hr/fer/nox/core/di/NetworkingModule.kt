package hr.fer.nox.core.di

import android.content.Context
import android.net.ConnectivityManager
import hr.fer.nox.core.BuildConfig
import hr.fer.nox.core.networking.ConnectivityManagerWrapper
import hr.fer.nox.core.networking.ConnectivityManagerWrapperImpl
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NetworkUtilsImpl
import hr.fer.nox.preferences.AccessToken
import hr.fer.nox.preferences.UserPreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

val NetworkingModule = module {

    single(named(NOX_BASE_URL_IDENTIFIER)) { "https://nox.herokuapp.com/" }

    single { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single<ConnectivityManagerWrapper> { ConnectivityManagerWrapperImpl(get()) }

    single<NetworkUtils> { NetworkUtilsImpl(androidApplication(), get()) }

    single {
        val userPreferences: UserPreferences = get()

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val token = userPreferences.getAccessToken()
                if (token != AccessToken.EMPTY) {
                    val newRequest = chain
                        .request()
                        .newBuilder()
                        .addHeader("Authorization", "Bearer ${token.token}")
                        .build()
                    chain.proceed(newRequest)
                } else {
                    chain.proceed(chain.request())
                }
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named(NOX_BASE_URL_IDENTIFIER)))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
    }

}

const val NOX_BASE_URL_IDENTIFIER = "NOX_URL"