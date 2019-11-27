package hr.fer.nox.movies.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.movies.resources.ComingSoonMoviesResources
import hr.fer.nox.movies.resources.MoviesResources
import hr.fer.nox.movies.resources.NewReleasesMoviesResources
import hr.fer.nox.movies.resources.PopularMoviesResources
import hr.fer.nox.movies.ui.container.MoviesContainerContract
import hr.fer.nox.movies.ui.container.MoviesContainerPresenter
import hr.fer.nox.movies.ui.movies.MoviesContract
import hr.fer.nox.movies.ui.movies.MoviesPresenter
import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movies.usecase.QueryNewReleasesMovies
import hr.fer.nox.movies.usecase.QueryPopularMovies
import hr.fer.nox.movies.usecase.QueryUpcomingMovies
import org.koin.core.qualifier.named
import org.koin.dsl.module

val MoviesModule = module {

    scope(named(MOVIES_CONTAINER_VIEW_SCOPE)) {

        scoped {
            val presenter = MoviesContainerPresenter().apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as MoviesContainerContract.Presenter
        }
    }

    scope(named(POPULAR_MOVIES_VIEW_SCOPE)) {

        scoped {
            val presenter = MoviesPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as MoviesContract.Presenter
        }

        scoped {
            PopularMoviesResources(get()) as MoviesResources
        }

        scoped<QueryMovieList> {
            QueryPopularMovies(get())
        }
    }

    scope(named(NEW_RELEASES_MOVIES_VIEW_SCOPE)) {

        scoped {
            val presenter = MoviesPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as MoviesContract.Presenter
        }

        scoped {
            NewReleasesMoviesResources(get()) as MoviesResources
        }

        scoped<QueryMovieList> {
            QueryNewReleasesMovies(get())
        }
    }

    scope(named(COMING_SOON_MOVIES_VIEW_SCOPE)) {

        scoped {
            val presenter = MoviesPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as MoviesContract.Presenter
        }

        scoped {
            ComingSoonMoviesResources(get()) as MoviesResources
        }

        scoped<QueryMovieList> {
            QueryUpcomingMovies(get())
        }
    }
}

const val MOVIES_CONTAINER_VIEW_SCOPE = "MoviesContainer view scope"
const val POPULAR_MOVIES_VIEW_SCOPE = "PopularMovies view scope"
const val NEW_RELEASES_MOVIES_VIEW_SCOPE = "NewReleasesMovies view scope"
const val COMING_SOON_MOVIES_VIEW_SCOPE = "ComingSoonMovies view scope"