package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCast(
    @Json(name = "id") val id: Int,
    @Json(name = "characterName") val characterName: String,
    @Json(name = "actor") val actor: String,
    @Json(name = "actorImagePath") val actorImagePath: String?
)