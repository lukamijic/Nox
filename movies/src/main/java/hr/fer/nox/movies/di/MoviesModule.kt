package hr.fer.nox.movies.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.movies.resources.movies.ComingSoonMoviesResources
import hr.fer.nox.movies.resources.MoviesResources
import hr.fer.nox.movies.resources.movies.NewReleasesMoviesResources
import hr.fer.nox.movies.resources.movies.PopularMoviesResources
import hr.fer.nox.movies.resources.recommendations.UserRecommendationsMoviesResources
import hr.fer.nox.movies.resources.recommendations.WeatherRecommendationsMoviesResources
import hr.fer.nox.movies.ui.container.MoviesContainerContract
import hr.fer.nox.movies.ui.container.MoviesContainerPresenter
import hr.fer.nox.movies.ui.movies.MoviesContract
import hr.fer.nox.movies.ui.movies.MoviesPresenter
import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movies.usecase.movies.QueryNewReleasesMovies
import hr.fer.nox.movies.usecase.movies.QueryPopularMovies
import hr.fer.nox.movies.usecase.movies.QueryUpcomingMovies
import hr.fer.nox.movies.usecase.recommendations.QueryUserRecommendations
import hr.fer.nox.movies.usecase.recommendations.QueryWeatherRecommendations
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

    scope(named(USER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE)) {

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
            UserRecommendationsMoviesResources(get()) as MoviesResources
        }

        scoped<QueryMovieList> {
            QueryUserRecommendations(get())
        }
    }

    scope(named(WEATHER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE)) {

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
            WeatherRecommendationsMoviesResources(get()) as MoviesResources
        }

        scoped<QueryMovieList> {
            QueryWeatherRecommendations(get(), get(), get(), get())
        }
    }
}

const val MOVIES_CONTAINER_VIEW_SCOPE = "MoviesContainer view scope"
const val POPULAR_MOVIES_VIEW_SCOPE = "PopularMovies view scope"
const val NEW_RELEASES_MOVIES_VIEW_SCOPE = "NewReleasesMovies view scope"
const val COMING_SOON_MOVIES_VIEW_SCOPE = "ComingSoonMovies view scope"
const val USER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE = "UserRecommendationsMovies view scope"
const val WEATHER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE = "WeatherRecommendationsMovies view scope"