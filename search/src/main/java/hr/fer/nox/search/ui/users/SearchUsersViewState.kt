package hr.fer.nox.search.ui.users

import hr.fer.nox.search.model.UserItemViewModel

data class SearchUsersViewState(
    var users: List<UserItemViewModel>,
    var isLoading: Boolean
)