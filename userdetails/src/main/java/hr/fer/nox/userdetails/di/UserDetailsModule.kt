package hr.fer.nox.userdetails.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.userdetails.ui.UserDetailsContract
import hr.fer.nox.userdetails.ui.UserDetailsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val UserDetailsModule = module {

    scope(named(USERS_DETAILS_VIEW_SCOPE)) {

        scoped {
            val userId: String = it[0]
            val presenter = UserDetailsPresenter(userId, get(), get(), get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as UserDetailsContract.Presenter
        }

    }
}

const val USERS_DETAILS_VIEW_SCOPE = "UsersDetails view scope"
