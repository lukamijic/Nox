package hr.fer.nox.userdetails.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface UserDetailsContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, UserDetailsViewState>
}