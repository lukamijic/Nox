package hr.fer.nox.userdetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.userdetails.R
import hr.fer.nox.userdetails.di.USERS_DETAILS_VIEW_SCOPE
import hr.fer.nox.userdetails.model.MovieItemViewModel
import hr.fer.nox.userdetails.model.UserItemViewModel
import hr.fer.nox.userdetails.ui.adapter.MoviesAdapter
import hr.fer.nox.userdetails.ui.adapter.UsersAdapter
import hr.fer.nox.userlib.service.UserService
import kotlinx.android.synthetic.main.fragment_user_details.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class UserDetailsFragment: BaseFragment<UserDetailsViewState>(), UserDetailsContract.View {

    companion object {

        const val TAG = "UserDetailsFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_user_details

        private const val USER_ID_KEY = "user_id"
        private const val IS_PROFILE_TAB = "is_profile_tab"

        fun newInstance(userId: String, isProfileTab: Boolean): Fragment =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID_KEY, userId)
                    putBoolean(IS_PROFILE_TAB, isProfileTab)
                }
            }
    }
    private val imageUtils: ImageUtils by inject()
    private val userService: UserService by inject()
    private val presenter: UserDetailsContract.Presenter by scopedInject(
        parameters = { parametersOf(getUserIdFromBundle(), isProfileTabFromBundle()) }
    )

    private lateinit var usersAdapter: UsersAdapter

    private lateinit var moviesAdapter: MoviesAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {

      /*  usersAdapter = UsersAdapter(
            LayoutInflater.from(context)
        ) { userItemViewModel: UserItemViewModel -> presenter.showUserDetails(userItemViewModel.userId) }
*/
        moviesAdapter = MoviesAdapter(
            LayoutInflater.from(context), imageUtils
        ) {movieItemViewModel : MovieItemViewModel -> presenter.showMovieDetails(movieItemViewModel.movieId)}


        userdetails_backButton.setOnClickListener { goBack() }

        /*user_details_followed_users.apply {
            layoutManager = GridLayoutManager(context, 1, RecyclerView.VERTICAL, false)
            adapter = usersAdapter
        }*/
        user_details_liked_movies.apply{
            adapter = moviesAdapter
            layoutManager =  GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
        }
    }

    override fun render(viewState: UserDetailsViewState) {
        with(viewState) {
            if (isLoading) {
                userdetails_progressView.isVisible = true
                userdetails_detailsContainer.isVisible = false
                return
            }
            imageUrl?.run{
                imageUtils.loadInto(this, user_details_profile_picture)
            }

            user_details_email.text = email
            user_details_name.text = name
            user_details_surname.text = surname
            if (isProfileTabFromBundle()){
                userdetails_toolbar.visibility = View.GONE
            } else{
                userdetails_toolbar.visibility = View.VISIBLE
            }
            /*
            if (!isMyProfile(userId)){
                user_details_follow_user.visibility = View.VISIBLE
                if (isUserFollowed(userId)) {
                    user_details_follow_user.text = getString(R.string.user_unfollow)
                } else {
                    user_details_follow_user.text = getString(R.string.user_follow)
                }
                user_details_follow_user.setOnClickListener{
                    val b: Button = (it as Button)
                    if (b.text == "Follow") {
                        userService.followUser(userId)
                        b.text = getString(R.string.user_unfollow)
                        // TODO: update the BaseFragment.loggedinDetails follow user list
                    } else {
                        userService.unfollowUser(userId)
                        b.text = getString(R.string.user_follow)
                        // TODO: update the BaseFragment.loggedinDetails follow user list
                    }
                }

                user_details_followed_users_label.visibility = View.GONE
                user_details_followed_users.visibility = View.GONE
                usersAdapter.submitList(emptyList())
            } else {


                user_details_follow_user.visibility = View.GONE


                viewState.followedUsers = BasePresenter.loggedUserDetails.likedUsers.map { user ->
                    UserItemViewModel(user.id, user.name, user.email)
                }

                if (viewState.followedUsers.count()>0) {
                    user_details_followed_users_label.visibility = View.VISIBLE
                    user_details_followed_users.visibility = View.VISIBLE
                    usersAdapter.submitList(viewState.followedUsers)
                } else {
                    user_details_followed_users_label.visibility = View.GONE
                    user_details_followed_users.visibility = View.GONE
                    usersAdapter.submitList(emptyList())
                }

            } */
            if (viewState.likedMovies.count() > 0) {
                moviesAdapter.submitList(viewState.likedMovies)
                user_details_liked_movies_label.visibility = View.VISIBLE
                user_details_liked_movies.visibility = View.VISIBLE
            } else {
                moviesAdapter.submitList(emptyList())
                user_details_liked_movies_label.visibility = View.GONE
                user_details_liked_movies.visibility = View.GONE
            }

        }
    }
    private fun goBack() {
        presenter.back()
    }
    private fun isMyProfile(userId: String): Boolean  {
       return  userId.equals(BasePresenter.loggedUserDetails.userDetails.id) || userId.equals("ME")
    }
    private fun isUserFollowed(userId: String): Boolean {
        BasePresenter.loggedUserDetails.likedUsers.forEach{
            if (it.id.equals(userId)) return true
        }
        return false
    }

    private fun getUserIdFromBundle(): String = arguments?.getString(USER_ID_KEY) ?: throw IllegalArgumentException("User id not found")
    private fun isProfileTabFromBundle(): Boolean = arguments?.getBoolean(IS_PROFILE_TAB) ?: throw java.lang.IllegalArgumentException("Is profile not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = USERS_DETAILS_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, UserDetailsViewState> = presenter as ViewPresenter<BaseView, UserDetailsViewState>
}