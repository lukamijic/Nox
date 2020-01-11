package hr.fer.nox.userlib.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiUserDetails(
    @Json(name = "id") val id: String,
    @Json(name = "firstName") val name: String?,
    @Json(name = "lastName") val surname: String?,
    @Json(name = "email") val email: String,
    @Json(name = "imageUrl") val imageUrl: String?,
    @Json(name = "providerId") val providerId: String?,
    @Json(name = "authProvider") val authProvider: String?
)
