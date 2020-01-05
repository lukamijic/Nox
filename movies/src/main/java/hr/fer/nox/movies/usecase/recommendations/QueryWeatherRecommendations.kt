package hr.fer.nox.movies.usecase.recommendations

import hr.fer.nox.location.LocationProvider
import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import hr.fer.nox.permissions.PermissionHandler
import hr.fer.nox.permissions.usecase.QueryLocationPermission
import io.reactivex.Flowable

class QueryWeatherRecommendations(
    private val movieSource: MovieSource,
    private val locationProvider: LocationProvider,
    private val permissionHandler: PermissionHandler,
    private val queryLocationPermission: QueryLocationPermission
): QueryMovieList {

    override fun invoke(): Flowable<List<Movie>> =
        queryLocationPermission()
            .filter {
                if (!it) {
                    permissionHandler.requestLocationPermission()
                }
                it
            }
            .switchMap {
                val location = locationProvider.getLocation()
                location?.run {
                    movieSource.getWeatherRecommendation(lat, long)
                } ?: Flowable.just(emptyList())
            }
}