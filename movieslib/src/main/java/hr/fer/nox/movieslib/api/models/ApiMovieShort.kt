package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovieShort(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val movieTitle: String,
    @Json(name = "poster_path") val posterPath: String?
)