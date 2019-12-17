package hr.fer.nox.search.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.search.ui.container.SearchContainerContract
import hr.fer.nox.search.ui.container.SearchContainerPresenter
import hr.fer.nox.search.ui.movies.SearchMoviesContract
import hr.fer.nox.search.ui.movies.SearchMoviesPresenter
import hr.fer.nox.search.ui.users.SearchUsersContract
import hr.fer.nox.search.ui.users.SearchUsersPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val SearchModule = module {

    scope(named(SEARCH_CONTAINER_VIEW_SCOPE)) {

        scoped {
            val presenter = SearchContainerPresenter().apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as SearchContainerContract.Presenter
        }
    }

    scope(named(SEARCH_MOVIES_VIEW_SCOPE)) {

        scoped {
            val presenter = SearchMoviesPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as SearchMoviesContract.Presenter
        }
    }

    scope(named(SEARCH_USERS_VIEW_SCOPE)) {
        scoped {
            val presenter = SearchUsersPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as SearchUsersContract.Presenter
        }
    }
}

const val SEARCH_CONTAINER_VIEW_SCOPE = "SearchContainer view scope"
const val SEARCH_MOVIES_VIEW_SCOPE = "SearchMovies view scope"
const val SEARCH_USERS_VIEW_SCOPE = "SearchUsers view scope"