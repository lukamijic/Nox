package hr.fer.nox.createaccount.ui

import hr.fer.nox.core.networking.ConnectionType
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NoConnection
import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.createaccount.resources.CreateAccountResources
import hr.fer.nox.navigation.router.Router
import io.reactivex.Completable

class CreateAccountPresenter(
    private val createAccountResources: CreateAccountResources,
    private val networkUtils: NetworkUtils
): BasePresenter<CreateAccountContract.View, CreateAccountViewState>(), CreateAccountContract.Presenter {

    override fun initialViewState(): CreateAccountViewState = CreateAccountViewState("", false)

    override fun createAccount(username: String, email: String, password: String) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            viewStateAction {
                errorMessage = createAccountResources.createAccountCredentialsEmptyErrorMessage()
                isLoading = false
            }

            return
        }

        runCommand(createAccountCommand(username, email, password))
    }

    private fun createAccountCommand(username: String, email: String, password: String): Completable {
        viewStateAction {
            errorMessage = ""
            isLoading = true
        }

        return networkUtils.connectionTypeSingle().flatMapCompletable { connectionType: ConnectionType -> createAccountInternal(username, email, password, connectionType) }
    }

    private fun createAccountInternal(username: String, email: String, password: String, connectionType: ConnectionType): Completable =
        if (connectionType is NoConnection) {
            Completable.fromAction {
                viewStateAction {
                    isLoading = false
                    errorMessage = createAccountResources.noInternetConnectionErrorMessage()
                }
            }
        } else {
            Completable.fromAction {  dispatchRoutingAction(Router::showHome) }
        }
}