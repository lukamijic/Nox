package hr.fer.nox.movies.usecase.recommendations

import hr.fer.nox.location.LocationProvider
import hr.fer.nox.location.model.Location
import hr.fer.nox.movies.usecase.QueryMovieList
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.source.MovieSource
import hr.fer.nox.permissions.PermissionHandler
import hr.fer.nox.permissions.usecase.QueryLocationPermission
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class QueryWeatherRecommendations(
    private val movieSource: MovieSource,
    private val locationProvider: LocationProvider,
    private val permissionHandler: PermissionHandler,
    private val queryLocationPermission: QueryLocationPermission
) : QueryMovieList {

    override fun invoke(): Flowable<List<Movie>> =
        queryLocationPermission()
            .observeOn(AndroidSchedulers.mainThread())
            .filter {
                if (!it) permissionHandler.requestLocationPermission()
                it
            }
            .map { locationProvider.getLocation() }
            .observeOn(Schedulers.newThread())
            .flatMap {
                if (it != Location.EMPTY) {
                    movieSource.getWeatherRecommendation(it.lat, it.long)
                } else {
                    Flowable.just(emptyList())
                }
            }
}