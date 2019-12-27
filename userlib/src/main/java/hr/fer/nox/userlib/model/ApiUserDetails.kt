package hr.fer.nox.userlib.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ApiUserDetails(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "surname") val surname: String,
    @Json(name = "email") val email: String,
    @Json(name = "gender") val gender: String,
    @Json(name = "age") val age: Int,
    @Json(name = "iq") val iq: Int?
)