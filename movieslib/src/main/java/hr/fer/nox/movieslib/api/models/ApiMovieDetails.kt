package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovieDetails(
    @Json(name = "id") val id: Int,
    @Json(name = "original_title") val title: String,
    @Json(name = "overview") val synopsis: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "genres") val genres: List<ApiGenres>,
    @Json(name = "runtime") val durationInMinutes: Int,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "videos") val videos: ApiVideos,
    @Json(name = "credits") val credits: ApiCredits
)