package hr.fer.nox.userlib.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiLoginRequest(
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String
)