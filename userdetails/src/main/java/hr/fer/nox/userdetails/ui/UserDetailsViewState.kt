package hr.fer.nox.userdetails.ui

import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.userdetails.model.MovieItemViewModel
import hr.fer.nox.userdetails.model.UserItemViewModel


data class UserDetailsViewState(
    var userId: String,
    var name: String?,
    var surname: String?,
    var email: String,
    var imageUrl: String?,
    var followedUsers: List<UserItemViewModel>,
    var likedMovies: List<MovieItemViewModel>,
    var isLoading: Boolean
) {

    companion object {

        val INITIAL = UserDetailsViewState(
           "",
            "",
            "",
            "",
            null,
            emptyList(),
            emptyList(),
            true
            )
    }
}
