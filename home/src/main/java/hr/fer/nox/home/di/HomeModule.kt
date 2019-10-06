package hr.fer.nox.home.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.home.ui.HomeContract
import hr.fer.nox.home.ui.HomePresenter
import org.koin.dsl.module.module

val HomeModule = module {
    scope(HOME_VIEW_SCOPE) {
        val presenter = HomePresenter().apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as HomeContract.Presenter
    }
}

const val HOME_VIEW_SCOPE = "Home view scope"