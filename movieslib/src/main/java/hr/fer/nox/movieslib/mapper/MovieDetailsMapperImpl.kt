package hr.fer.nox.movieslib.mapper

import hr.fer.nox.movieslib.api.models.ApiMovieDetails
import hr.fer.nox.movieslib.model.Actor
import hr.fer.nox.movieslib.model.MovieDetails

class MovieDetailsMapperImpl(
    private val baseImageUrl: String
): MovieDetailsMapper {

    companion object {

        private const val DIRECTOR_JOB_TITLE = "director"
        private const val TRAILER_VIDEO_TYPE = "trailer"
        private const val YOUTUBE_VIDEO = "youtube"
    }

    override fun map(apiMovieDetails: ApiMovieDetails, isLiked: Boolean): MovieDetails {
        val directorName = apiMovieDetails.credits.crew.firstOrNull { it.job.toLowerCase() == DIRECTOR_JOB_TITLE }?.name ?: ""
        val videos = apiMovieDetails.videos.videos

        val videoYoutubeUrl = videos.firstOrNull { it.site.toLowerCase() == YOUTUBE_VIDEO && it.type.toLowerCase() == TRAILER_VIDEO_TYPE}?.key
            ?: (videos.firstOrNull { it.site.toLowerCase() == YOUTUBE_VIDEO }?.key ?: "")

        return with(apiMovieDetails) {
            MovieDetails(
                id,
                title,
                releaseDate.split("-").getOrNull(0)?.toIntOrNull() ?: 0,
                durationInMinutes,
                genres.map { it.genreName },
                videoYoutubeUrl,
                isLiked,
                posterPath?.run { baseImageUrl + this },
                synopsis,
                "8.5/10",
                "83%",
                "75/100",
                directorName,
                credits.cast.map { Actor(it.actor, it.characterName, it.actorImagePath?.run { baseImageUrl+ this }) }
            )
        }
    }
}