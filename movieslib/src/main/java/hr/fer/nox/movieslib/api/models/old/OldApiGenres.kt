package hr.fer.nox.movieslib.api.models.old

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OldApiGenres(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val genreName: String
)