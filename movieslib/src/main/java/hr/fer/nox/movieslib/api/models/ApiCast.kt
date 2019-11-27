package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCast(
    @Json(name = "cast_id") val id: Int,
    @Json(name = "character") val characterName: String,
    @Json(name = "name") val actor: String,
    @Json(name = "profile_path") val actorImagePath: String?
)