package hr.fer.nox.movieslib.api.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiCredits(
    @Json(name = "cast") val cast: List<ApiCast>,
    @Json(name = "crew") val crew: List<ApiCrew>
)
