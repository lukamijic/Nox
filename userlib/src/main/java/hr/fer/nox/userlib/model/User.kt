package hr.fer.nox.userlib.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    val id: String = "",
    val name: String = "",
    val surname: String = "",
    val email: String = ""
)