package hr.fer.nox.moviedetails.model

import hr.fer.nox.coreui.util.DiffUtilViewModel

data class ActorViewModel(
    val actorName: String,
    val actorRoleName: String,
    val actorImageUrl: String?
): DiffUtilViewModel()