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
import hr.fer.nox.navigation.model.UserInfo
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

class UserDetailsFragment : BaseFragment<UserDetailsViewState>(), UserDetailsContract.View {

    companion object {

        const val TAG = "UserDetailsFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_user_details

        private const val USER_ID_KEY = "user_id"

        fun newInstance(userInfo: UserInfo): Fragment =
            UserDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(USER_ID_KEY, userInfo.userId)
                }
            }
    }

    private val imageUtils: ImageUtils by inject()
    private val presenter: UserDetailsContract.Presenter by scopedInject(
        parameters = { parametersOf(getUserIdFromBundle(), isProfileTabFromBundle()) }
    )

    private lateinit var moviesAdapter: MoviesAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter(
            LayoutInflater.from(context), imageUtils
        ) { movieItemViewModel: MovieItemViewModel -> presenter.showMovieDetails(movieItemViewModel.movieId) }

        userdetails_backButton.setOnClickListener { goBack() }

        user_details_liked_movies.apply {
            adapter = moviesAdapter
            layoutManager = GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)
            OverScrollDecoratorHelper.setUpOverScroll(
                this,
                OverScrollDecoratorHelper.ORIENTATION_VERTICAL
            )
        }
    }

    override fun render(viewState: UserDetailsViewState) {
        with(viewState) {
            userdetails_progressView.isVisible = isLoading
            userdetails_detailsContainer.isVisible = !isLoading


            user_details_email.text = email
            user_details_name.text = name
            user_details_surname.text = surname
            if (isProfileTabFromBundle()) {
                userdetails_toolbar.visibility = View.GONE
            } else {
                userdetails_toolbar.visibility = View.VISIBLE
            }

            moviesAdapter.submitList(viewState.likedMovies)
            user_details_liked_movies_label.isVisible = likedMovies.isNotEmpty()
            user_details_liked_movies.isVisible = likedMovies.isNotEmpty()
        }
    }

    private fun goBack() {
        presenter.back()
    }

    private fun getUserIdFromBundle(): String =
        arguments?.getString(USER_ID_KEY) ?: throw IllegalArgumentException("User id not found")

    private fun isProfileTabFromBundle(): Boolean =
        arguments?.getString(USER_ID_KEY)?.run { this == UserInfo.ME.userId }
            ?: throw java.lang.IllegalArgumentException("Is profile not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = USERS_DETAILS_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, UserDetailsViewState> =
        presenter as ViewPresenter<BaseView, UserDetailsViewState>
}
