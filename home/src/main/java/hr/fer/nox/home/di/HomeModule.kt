package hr.fer.nox.home.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.home.ui.HomeContract
import hr.fer.nox.home.ui.HomePresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val HomeModule = module {
    scope(named(HOME_VIEW_SCOPE)) {

        scoped {
            val presenter = HomePresenter().apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as HomeContract.Presenter
        }
    }
}

const val HOME_VIEW_SCOPE = "Home view scope"
