package hr.fer.nox.moviedetails.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.moviedetails.ui.MovieDetailsContract
import hr.fer.nox.moviedetails.ui.MovieDetailsPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val MovieDetailsModule = module {

    scope(named(MOVIES_DETAILS_VIEW_SCOPE)) {

        scoped {
            val movieId: String = it[0]
            val presenter = MovieDetailsPresenter(movieId).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as MovieDetailsContract.Presenter
        }
    }
}

const val MOVIES_DETAILS_VIEW_SCOPE = "MoviesDetails view scope"