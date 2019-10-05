package hr.fer.nox.splash.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.splash.ui.SplashContract
import hr.fer.nox.splash.ui.SplashPresenter
import org.koin.dsl.module.module

val SplashModule = module {
    scope(SPLASH_VIEW_SCOPE) {
        SplashPresenter(it[0]).apply {
            mainThreadScheduler = get(MAIN_SCHEDULER)
            backgroundScheduler = get(BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        } as SplashContract.Presenter
    }
}

const val SPLASH_VIEW_SCOPE = "Splash view scope"