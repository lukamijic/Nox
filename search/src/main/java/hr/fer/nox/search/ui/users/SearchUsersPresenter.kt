package hr.fer.nox.search.ui.users

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.navigation.model.UserInfo

import hr.fer.nox.userlib.usecase.QuerySearchUsers
import hr.fer.nox.userlib.model.User
import hr.fer.nox.search.model.UserItemViewModel
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.usecase.GetAllUsers
import hr.fer.nox.userlib.usecase.SearchUsers

class SearchUsersPresenter(
    private val getAllUsers: GetAllUsers,
    private val searchUsers: SearchUsers,
    private val querySearchUsers: QuerySearchUsers
) : BasePresenter<SearchUsersContract.View, SearchUsersViewState>(), SearchUsersContract.Presenter {

    override fun initialViewState(): SearchUsersViewState = SearchUsersViewState(emptyList(), false)


    override fun onStart() {
        query(
            getAllUsers()
                .map(this::toViewStateAction)
        )

        query(
            querySearchUsers()
                .map(this::toSearchViewStateAction)
        )
    }

    override fun searchUsers(searchTerm: String) {
        if (searchTerm.length >= 3) {
            mutateViewState {
                it.isLoading = true
                it.users = emptyList()
            }
            runCommand(searchUsers.invoke(searchTerm))
        } else if (searchTerm.isEmpty()) {
            mutateViewState {
                it.isLoading = false
            }
        }

    }

    override fun showUserDetails(userId: String) {
        dispatchRoutingAction { router -> router.showUserDetails(UserInfo(userId)) }
    }

    private fun toViewStateAction(users: List<User>): (SearchUsersViewState) -> Unit = {
        it.isLoading = false
        it.users = users.map {
                user -> UserItemViewModel(user.id, "${user.name} ${user.surname}", user.email)
        }
    }

    private fun toSearchViewStateAction(users: List<UserDetails>): (SearchUsersViewState) -> Unit = {
        it.isLoading = false
        it.users = users.map {
                user -> UserItemViewModel(user.id, "${user.name} ${user.surname}", user.email)
        }
    }
}
