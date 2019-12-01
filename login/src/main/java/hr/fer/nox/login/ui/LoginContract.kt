package hr.fer.nox.login.ui

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface LoginContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, LoginViewState> {

        fun login(email: String, password: String)

        fun facebookLogin(accessToken: AccessToken)

        fun googleLogin(googleSignInAccount: GoogleSignInAccount)

        fun showCreateAccount()
    }
}