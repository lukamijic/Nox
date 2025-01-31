package hr.fer.nox.userdetails.model

import hr.fer.nox.coreui.util.DiffUtilViewModel


data class UserItemViewModel(
    val userId: String,
    val name: String?,
    val email: String
): DiffUtilViewModel(userId)