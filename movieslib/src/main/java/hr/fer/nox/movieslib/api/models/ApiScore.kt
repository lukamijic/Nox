package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiScore(
    @Json(name ="imdb") val imdbScore: String,
    @Json(name = "metascore") val metacriticScore: String,
    @Json(name = "rotten") val rottenTomatoScore: String
)