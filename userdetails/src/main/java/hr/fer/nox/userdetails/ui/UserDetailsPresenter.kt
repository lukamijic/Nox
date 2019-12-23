package hr.fer.nox.userdetails.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.usecase.QueryUserDetails


class UserDetailsPresenter(
    private val userId: String,
    private val queryUserDetails: QueryUserDetails
): BasePresenter<UserDetailsContract.View, UserDetailsViewState>(), UserDetailsContract.Presenter {

    override fun initialViewState(): UserDetailsViewState = UserDetailsViewState.EMPTY

    override fun onStart() {
        query(
            queryUserDetails(userId)
                .map(this::toViewStateAction)
        )
    }

    private fun toViewStateAction(userDetails: UserDetails): (UserDetailsViewState) -> Unit = {
        with(userDetails) {
            it.userId = userId
        }
    }

}