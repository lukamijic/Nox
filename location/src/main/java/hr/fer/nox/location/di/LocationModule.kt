package hr.fer.nox.location.di

import android.content.Context
import android.location.LocationManager
import hr.fer.nox.location.LocationProvider
import hr.fer.nox.location.LocationProviderImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val LocationModule = module {

    single { androidApplication().getSystemService(Context.LOCATION_SERVICE) as LocationManager }

    single<LocationProvider> { LocationProviderImpl(get()) }
}