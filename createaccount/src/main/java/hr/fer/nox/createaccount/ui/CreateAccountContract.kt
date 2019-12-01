package hr.fer.nox.createaccount.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface CreateAccountContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, CreateAccountViewState> {

        fun createAccount(name: String, surname: String, email: String, password: String)
    }
}