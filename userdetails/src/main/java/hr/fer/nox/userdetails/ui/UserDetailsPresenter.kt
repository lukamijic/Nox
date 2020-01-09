package hr.fer.nox.userdetails.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.usecase.QueryLikedMovies
import hr.fer.nox.userdetails.model.MovieItemViewModel
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.usecase.QueryUserDetails


class UserDetailsPresenter(
    private var userId: String,
    private val queryUserDetails: QueryUserDetails,
    private val queryLikedMovies: QueryLikedMovies
): BasePresenter<UserDetailsContract.View, UserDetailsViewState>(), UserDetailsContract.Presenter {

    override fun initialViewState(): UserDetailsViewState = UserDetailsViewState.EMPTY


    override fun onStart() {
        if (userId.equals("ME")){
            userId = loggedUserDetails.userDetails.id
        }
        query(
            queryUserDetails(userId)
                .map(this::toViewStateAction)
        )
        query(
            queryLikedMovies(userId).map(this::toViewStateAction)
        )
    }

    private fun toViewStateAction(userDetails: UserDetails): (UserDetailsViewState) -> Unit = {

        with(userDetails) {
            it.userId = userId
            it.email = email
            it.isLoading = false
            it.name = name
            it.surname = surname
        }
    }

    private fun toViewStateAction(movies : List<Movie>): (UserDetailsViewState) -> Unit = {
        it.likedMovies = movies.map { movie ->
            MovieItemViewModel(movie.id, movie.moviePosterPath)
        }
    }

    override fun showUserDetails(userId: String) {
        dispatchRoutingAction { router -> router.showUserDetails(userId, false) }
    }

    override fun showMovieDetails(movieId: Int) {
        dispatchRoutingAction { router -> router.showMovieDetails(movieId) }
    }

}