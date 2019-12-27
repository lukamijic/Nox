package hr.fer.nox.userlib.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiUsersList(
    @Json(name = "results") val results: List<ApiUserShort>
)