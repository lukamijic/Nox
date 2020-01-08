package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovieDetails(
    @Json(name = "movieDBid") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "synopsis") val synopsis: String,
    @Json(name = "releaseDate") val releaseDate: String,
    @Json(name = "genres") val genres: List<String>?,
    @Json(name = "runtimeInMinutes") val runtimeInMinutes: Int?,
    @Json(name = "posterPath") val posterPath: String?,
    @Json(name = "score") val score: ApiScore,
    @Json(name = "videos") val videos: List<ApiVideo>?,
    @Json(name = "credits") val credits: ApiCredits?
)
