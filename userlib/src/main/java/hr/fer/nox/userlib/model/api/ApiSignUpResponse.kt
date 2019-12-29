package hr.fer.nox.userlib.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiSignUpResponse(
    @Json(name = "success") val success: Boolean,
    @Json(name = "message") val message: String
)