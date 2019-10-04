package hr.fer.nox.app

import android.app.Application
import com.facebook.stetho.Stetho
import hr.fer.nox.BuildConfig
import hr.fer.nox.app.di.AppModule
import hr.fer.nox.core.di.ThreadingModule
import hr.fer.nox.navigation.di.NavigationModule
import org.koin.android.ext.android.startKoin

class NoxApp: Application() {

    companion object {

        private const val TAG = "NoxApp"

        lateinit var instance: NoxApp
            private set
    }

    private val coreModules = listOf(AppModule, NavigationModule, ThreadingModule)

    //private val featureModules = listOf()

    override fun onCreate() {
        super.onCreate()

        instance = this
        startKoin(this, coreModules)
        initStetho()
    }

    private fun initStetho() {
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}