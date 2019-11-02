package hr.fer.nox.moviedetails.ui

import hr.fer.nox.coreui.base.BasePresenter
import io.reactivex.Flowable
import java.util.concurrent.TimeUnit

class MovieDetailsPresenter(
    private val movieId: String
): BasePresenter<MovieDetailsContract.View, MovieDetailsViewState>(), MovieDetailsContract.Presenter {

    override fun initialViewState(): MovieDetailsViewState = MovieDetailsViewState.EMPTY

    override fun onStart() {
        query(
            Flowable.just(MovieDetailsViewState.JOKER)
                .delay(500, TimeUnit.MILLISECONDS)
                .map { { viewState: MovieDetailsViewState ->
                    with(viewState) {
                        movieId = it.movieId
                        movieTitle = it.movieTitle
                        movieYearRelease = it.movieYearRelease
                        movieDuration = it.movieDuration
                        movieGenres = it.movieGenres
                        videoId = it.videoId
                        isLiked = it.isLiked
                        isOnWatchList = it.isOnWatchList
                        isWatched = it.isWatched
                        moviePosterUrl = it.moviePosterUrl
                        movieSynopsis = it.movieSynopsis
                        imdbScore = it.imdbScore
                        tomatoScore = it.tomatoScore
                        metacriticScore = it.metacriticScore
                        directorName = it.directorName
                        actors = it.actors
                        isLoading = it.isLoading
                    }

                } }
        )
    }
}