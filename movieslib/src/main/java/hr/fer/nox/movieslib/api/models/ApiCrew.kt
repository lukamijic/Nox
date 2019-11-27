package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCrew(
    @Json(name = "credit_id") val id: String,
    @Json(name = "job") val job: String,
    @Json(name = "name") val name: String,
    @Json(name = "profile_path") val actorImagePath: String?
)