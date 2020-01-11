package hr.fer.nox.userdetails.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.movieslib.model.Movie
import hr.fer.nox.movieslib.usecase.QueryLikedMovies
import hr.fer.nox.movieslib.usecase.QueryMyLikedMovies
import hr.fer.nox.navigation.model.UserInfo
import hr.fer.nox.userdetails.model.MovieItemViewModel
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.usecase.QueryMyUserDetails
import hr.fer.nox.userlib.usecase.QueryUserDetails
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

class UserDetailsPresenter(
    private var userId: String,
    private val queryMyUserDetails: QueryMyUserDetails,
    private val queryUserDetails: QueryUserDetails,
    private val queryLikedMovies: QueryLikedMovies,
    private val queryMyLikedMovies: QueryMyLikedMovies
): BasePresenter<UserDetailsContract.View, UserDetailsViewState>(), UserDetailsContract.Presenter {

    override fun initialViewState(): UserDetailsViewState = UserDetailsViewState.INITIAL

    override fun onStart() {
        query(
            Flowable.combineLatest(
                userFlowable(),
                moviesFlowable(),
                BiFunction(::toViewStateAction)
            )
        )
    }

    private fun userFlowable(): Flowable<UserDetails> =
        if (userId == UserInfo.ME.userId) {
            queryMyUserDetails()
        } else {
            queryUserDetails(userId)
        }

    private fun moviesFlowable(): Flowable<List<Movie>> =
        if (userId == UserInfo.ME.userId) {
            queryMyLikedMovies()
        } else {
            queryLikedMovies(userId)
        }

    private fun toViewStateAction(userDetails: UserDetails, movies : List<Movie>): (UserDetailsViewState) -> Unit = {
        with(userDetails) {
            it.userId = userId
            it.email = email
            it.isLoading = false
            it.name = name
            it.surname = surname
            it.likedMovies = movies.map { movie ->
                MovieItemViewModel(movie.id, movie.moviePosterPath)
            }
        }
    }

    override fun showUserDetails(userId: String) {
        //dispatchRoutingAction { router -> router.showUserDetails(userId, false) }
    }

    override fun showMovieDetails(movieId: Int) {
        dispatchRoutingAction { router -> router.showMovieDetails(movieId) }
    }

}
