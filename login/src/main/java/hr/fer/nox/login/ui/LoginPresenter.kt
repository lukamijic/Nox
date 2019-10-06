package hr.fer.nox.login.ui

import hr.fer.nox.core.networking.ConnectionType
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NoConnection
import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.login.resources.LoginResources
import io.reactivex.Completable

class LoginPresenter(
    private val loginResources: LoginResources,
    private val networkUtils: NetworkUtils
): BasePresenter<LoginContract.View, LoginViewState>(), LoginContract.Presenter {

    override fun initialViewState(): LoginViewState = LoginViewState()

    override fun login(username: String, password: String) {
        if (username.isEmpty() || password.isEmpty()) {
            viewStateAction { this.errorMessage = loginResources.loginCredentialsEmptyErrorMessage() }
            return
        }

        runCommand(loginCommand(username, password))
    }

    override fun showCreateAccount() {

    }

    private fun loginCommand(username: String, password: String): Completable {
        viewStateAction {
            isLoading = true
            errorMessage = ""
        }

        return networkUtils.connectionTypeSingle().flatMapCompletable { connectionType: ConnectionType -> login(username, password, connectionType) }
    }

    private fun login(username: String, password: String, connectionType: ConnectionType): Completable =
        if (connectionType is NoConnection) {
            Completable.fromAction {
                viewStateAction {
                    isLoading = false
                    errorMessage = loginResources.noInternetConnectionErrorMessage()
                }
            }
        } else {
            Completable.fromAction {  }
        }
}