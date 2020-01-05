package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiDirector(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String
)