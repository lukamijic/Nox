package hr.fer.nox.movieslib.api.models.old

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OldApiCredits(
    @Json(name = "cast") val cast: List<OldApiCast>,
    @Json(name = "crew") val crew: List<OldApiCrew>
)
