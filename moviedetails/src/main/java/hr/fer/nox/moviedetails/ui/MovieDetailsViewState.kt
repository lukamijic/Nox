package hr.fer.nox.moviedetails.ui

import hr.fer.nox.moviedetails.model.ActorViewModel

data class MovieDetailsViewState(
    var movieId: Int,
    var movieTitle: String,
    var movieYearRelease: Int,
    var movieDuration: String,
    var movieGenres: String,
    var videoId: String,
    var isLiked: Boolean,
    var isOnWatchList: Boolean,
    var isWatched: Boolean,
    var moviePosterUrl: String?,
    var movieSynopsis: String,
    var imdbScore: String,
    var tomatoScore: String,
    var metacriticScore: String,
    var directorName: String,
    var actors: List<ActorViewModel>,
    var isLoading: Boolean
) {

    companion object {

        val EMPTY = MovieDetailsViewState(
            -1,
            "",
            0,
            "",
            "",
            "",
            false,
            false,
            false,
            null,
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            true)
    }
}