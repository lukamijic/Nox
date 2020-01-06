package hr.fer.nox.userdetails.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.userdetails.R
import hr.fer.nox.userdetails.di.USERS_DETAILS_VIEW_SCOPE
import kotlinx.android.synthetic.main.fragment_user_details.*
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

    private val presenter: UserDetailsContract.Presenter by scopedInject(
        parameters = { parametersOf(getUserIdFromBundle(), isProfileTabFromBundle()) }
    )


    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        userdetails_backButton.setOnClickListener { goBack() }
    }

    override fun render(viewState: UserDetailsViewState) {
        with(viewState) {
            if (isLoading) {
                userdetails_progressView.isVisible = true
                userdetails_detailsContainer.isVisible = false
                return
            }
            user_details_email.text = email
            user_details_name.text = name
            user_details_surname.text = surname
            if (isProfileTabFromBundle()){
                userdetails_toolbar.visibility = View.GONE
            } else{
                userdetails_toolbar.visibility = View.VISIBLE
            }

        }
    }
    private fun goBack() {
        presenter.back()
    }

    private fun getUserIdFromBundle(): String = arguments?.getString(USER_ID_KEY) ?: throw IllegalArgumentException("User id not found")
    private fun isProfileTabFromBundle(): Boolean = arguments?.getBoolean(IS_PROFILE_TAB) ?: throw java.lang.IllegalArgumentException("Is profile not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = USERS_DETAILS_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, UserDetailsViewState> = presenter as ViewPresenter<BaseView, UserDetailsViewState>
}