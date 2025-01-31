package hr.fer.nox.moviedetails.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.moviedetails.model.ActorViewModel
import hr.fer.nox.moviedetails.resources.MovieDetailsResources
import hr.fer.nox.movieslib.model.MovieDetails
import hr.fer.nox.movieslib.usecase.LikeMovie
import hr.fer.nox.movieslib.usecase.QueryIsMyMovieLiked
import hr.fer.nox.movieslib.usecase.QueryMovieDetails
import hr.fer.nox.movieslib.usecase.UnlikeMovie
import io.reactivex.Completable
import java.lang.StringBuilder

class MovieDetailsPresenter(
    private val movieId: Int,
    private val movieDetailsResources: MovieDetailsResources,
    private val queryMovieDetails: QueryMovieDetails,
    private val queryIsMyMovieLiked: QueryIsMyMovieLiked,
    private val likeMovie: LikeMovie,
    private val unlikeMovie: UnlikeMovie
) : BasePresenter<MovieDetailsContract.View, MovieDetailsViewState>(),
    MovieDetailsContract.Presenter {

    override fun initialViewState(): MovieDetailsViewState = MovieDetailsViewState.EMPTY

    override fun onStart() {
        query(
            queryMovieDetails(movieId)
                .map(this::toViewStateAction)
        )

        runCommand(isMovieLiked())
    }

    override fun likeAction() {
        runCommand(
            queryIsMyMovieLiked(movieId)
                .firstOrError()
                .flatMapCompletable { isLiked ->
                    if (isLiked) {
                        unlikeMovie(movieId)
                    } else {
                        likeMovie(movieId)
                    }
                }.andThen(isMovieLiked())
        )
    }

    private fun isMovieLiked(): Completable =
        queryIsMyMovieLiked(movieId)
            .firstOrError()
            .flatMapCompletable { isLiked ->
                Completable.fromAction {
                    mutateViewState {
                        it.isLiked = isLiked
                    }
                }
            }

    private fun toViewStateAction(movieDetails: MovieDetails): (MovieDetailsViewState) -> Unit = {
        with(movieDetails) {
            it.movieId = movieId
            it.movieTitle = movieTitle
            it.movieYearRelease = movieYearRelease
            it.movieDuration = movieDetailsResources.getMovieDuration(movieDurationInMinutes)
            it.movieGenres = getGenresText(movieGenres)
            it.videoId = videoId
            it.isOnWatchList = false
            it.isWatched = false
            it.moviePosterUrl = moviePosterUrl ?: ""
            it.movieSynopsis = movieSynopsis
            it.imdbScore = imdbScore
            it.tomatoScore = tomatoScore
            it.metacriticScore = metacriticScore
            it.directorName = directorName
            it.actors =
                actors.map { ActorViewModel(it.actorName, it.characterName, it.actorImageUrl) }
            it.isLoading = false
        }
    }

    private fun getGenresText(genres: List<String>): String {
        val sb = StringBuilder()
        genres.forEachIndexed { index, s ->
            sb.append(s)
            if (index != genres.size - 1) {
                sb.append(", ")
            }
        }
        return sb.toString()
    }
}