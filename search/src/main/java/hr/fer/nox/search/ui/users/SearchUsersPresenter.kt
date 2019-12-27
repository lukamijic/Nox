package hr.fer.nox.search.ui.users

import hr.fer.nox.coreui.base.BasePresenter

import hr.fer.nox.userlib.usecase.QuerySearchUsers
import hr.fer.nox.userlib.model.User
import hr.fer.nox.search.model.UserItemViewModel
import hr.fer.nox.userlib.usecase.SearchUsers

class SearchUsersPresenter(
    private val querySearchUsers: QuerySearchUsers,
    private val searchUsers: SearchUsers
) : BasePresenter<SearchUsersContract.View, SearchUsersViewState>(), SearchUsersContract.Presenter {

    override fun initialViewState(): SearchUsersViewState = SearchUsersViewState(emptyList(), false)


    override fun onStart() {
        query(
            querySearchUsers()
                .map(this::toViewStateAction)
        )

    }

    override fun searchUsers(searchTerm: String) {
        if (searchTerm.length >= 3) {
            mutateViewState { it.isLoading = true }
            runCommand(searchUsers.invoke(searchTerm))
        } else if (searchTerm.isEmpty()) {
            mutateViewState {
                it.isLoading = false
                it.users = emptyList()
            }
        }

    }

    override fun showUserDetails(userId: String) {
        dispatchRoutingAction { router -> router.showUserDetails(userId, false) }
    }

    private fun toViewStateAction(users: List<User>): (SearchUsersViewState) -> Unit = {
        it.isLoading = false
        it.users = users.map {
                user -> UserItemViewModel(user.id, user.name, user.email)
        }
    }
}