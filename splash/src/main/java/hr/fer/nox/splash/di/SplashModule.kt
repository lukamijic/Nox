package hr.fer.nox.splash.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.splash.ui.SplashContract
import hr.fer.nox.splash.ui.SplashPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SplashModule = module {
    scope(named(SPLASH_VIEW_SCOPE)) {

        scoped {
            SplashPresenter(it[0]).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            } as SplashContract.Presenter
        }
    }
}

const val SPLASH_VIEW_SCOPE = "Splash view scope"