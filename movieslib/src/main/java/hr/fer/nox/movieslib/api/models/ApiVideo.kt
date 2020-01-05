package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiVideo(
    @Json(name = "id") val id: String,
    @Json(name = "key") val key: String,
    @Json(name = "videoName") val videoName: String,
    @Json(name = "site") val site: String,
    @Json(name = "type") val type: String
)