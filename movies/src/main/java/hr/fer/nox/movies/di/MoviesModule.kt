package hr.fer.nox.movies.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.movies.resources.MoviesResources
import hr.fer.nox.movies.resources.NewReleasesMoviesResources
import hr.fer.nox.movies.resources.PopularMoviesResources
import hr.fer.nox.movies.ui.container.MoviesContainerContract
import hr.fer.nox.movies.ui.container.MoviesContainerPresenter
import hr.fer.nox.movies.ui.movies.MoviesContract
import hr.fer.nox.movies.ui.movies.MoviesPresenter
import org.koin.dsl.module.module

val MoviesModule = module {

    scope(MOVIES_CONTAINER_VIEW_SCOPE) {
        val presenter = MoviesContainerPresenter().apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as MoviesContainerContract.Presenter
    }

    scope(POPULAR_MOVIES_VIEW_SCOPE) {
        val presenter = MoviesPresenter(get(scopeId = POPULAR_MOVIES_VIEW_SCOPE)).apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as MoviesContract.Presenter
    }

    scope(POPULAR_MOVIES_VIEW_SCOPE) {
        PopularMoviesResources(get()) as MoviesResources
    }

    scope(NEW_RELEASES_MOVIES_VIEW_SCOPE) {
        val presenter = MoviesPresenter(get(scopeId = NEW_RELEASES_MOVIES_VIEW_SCOPE)).apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as MoviesContract.Presenter
    }

    scope(NEW_RELEASES_MOVIES_VIEW_SCOPE) {
        NewReleasesMoviesResources(get()) as MoviesResources
    }

    /*scope(COMING_SOON_MOVIES_VIEW_SCOPE) {
        val presenter = MoviesPresenter(get(scopeId = COMING_SOON_MOVIES_VIEW_SCOPE)).apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as MoviesContract.Presenter
    }

    scope(COMING_SOON_MOVIES_VIEW_SCOPE) {
        PopularMoviesResources(get()) as MoviesResources
    }*/

}

const val MOVIES_CONTAINER_VIEW_SCOPE = "MoviesContainer view scope"
const val POPULAR_MOVIES_VIEW_SCOPE = "PopularMovies view scope"
const val NEW_RELEASES_MOVIES_VIEW_SCOPE = "NewReleasesMovies view scope"
const val COMING_SOON_MOVIES_VIEW_SCOPE = "ComingSoonMovies view scope"