package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiMoviesList(
    @Json(name = "results") val results: List<ApiMovieShort>
)