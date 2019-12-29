package hr.fer.nox.movieslib.api.models.old

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OldApiVideo(
    @Json(name = "id") val id: String,
    @Json(name = "key") val key: String,
    @Json(name = "name") val videoName: String,
    @Json(name = "site") val site: String,
    @Json(name = "type") val type: String
)