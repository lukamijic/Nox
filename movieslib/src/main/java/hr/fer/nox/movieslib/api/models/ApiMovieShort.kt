package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMovieShort(
    @Json(name = "movieId") val id: Int,
    @Json(name = "url") val posterPath: String?
)