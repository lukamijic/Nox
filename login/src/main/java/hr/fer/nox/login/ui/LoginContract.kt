package hr.fer.nox.login.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface LoginContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, LoginViewState> {

        fun login(username: String, password: String)

        fun showCreateAccount()
    }
}