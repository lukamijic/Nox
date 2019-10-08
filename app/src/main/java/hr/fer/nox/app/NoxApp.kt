package hr.fer.nox.app

import android.app.Application
import com.facebook.stetho.Stetho
import hr.fer.nox.BuildConfig
import hr.fer.nox.app.di.AppModule
import hr.fer.nox.core.di.NetworkingModule
import hr.fer.nox.core.di.ThreadingModule
import hr.fer.nox.home.di.HomeModule
import hr.fer.nox.login.di.LoginModule
import hr.fer.nox.movies.di.MoviesModule
import hr.fer.nox.navigation.di.NavigationModule
import hr.fer.nox.splash.di.SplashModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NoxApp: Application() {

    companion object {

        private const val TAG = "NoxApp"

        lateinit var instance: NoxApp
            private set
    }

    private val coreModules = listOf(AppModule, NavigationModule, ThreadingModule, NetworkingModule)

    private val featureModules = listOf(SplashModule, LoginModule, HomeModule, MoviesModule)

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin {
            androidContext(this@NoxApp)
            modules(coreModules + featureModules)
        }
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}