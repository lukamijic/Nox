package hr.fer.nox.search.ui.users

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface SearchUsersContract {

    interface View: BaseView

    interface Presenter: ViewPresenter<View, SearchUsersViewState> {

        fun searchUsers(searchTerm: String)

        fun showUserDetails(userId: String)
    }
}