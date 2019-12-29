package hr.fer.nox.movieslib.api.models.old

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OldApiMoviesList(
    @Json(name = "results") val results: List<OldApiMovieShort>
)