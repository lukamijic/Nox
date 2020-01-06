package hr.fer.nox.userlib.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.security.AuthProvider

@JsonClass(generateAdapter = true)
data class ApiUserShort(
    @Json(name = "id") val id: String,
    @Json(name = "firstName") val name: String?,
    @Json(name = "lastName") val surname: String?,
    @Json(name = "email") val email: String,
    @Json(name = "password") val password: String?,
    @Json(name = "imageUrl") val imageUrl: String?,
    @Json(name = "providerId") val providerId: String?,
    @Json(name = "authProvider") val authProvider: String?
)