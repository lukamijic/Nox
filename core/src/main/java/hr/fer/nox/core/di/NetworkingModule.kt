package hr.fer.nox.core.di

import android.content.Context
import android.net.ConnectivityManager
import hr.fer.nox.core.networking.ConnectivityManagerWrapper
import hr.fer.nox.core.networking.ConnectivityManagerWrapperImpl
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NetworkUtilsImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val NetworkingModule = module {

    single { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }

    single<ConnectivityManagerWrapper> { ConnectivityManagerWrapperImpl(get()) }

    single<NetworkUtils> { NetworkUtilsImpl(androidApplication(), get()) }
}