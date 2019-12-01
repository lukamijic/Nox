package hr.fer.nox.createaccount.ui

import hr.fer.nox.core.networking.ConnectionType
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NoConnection
import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.createaccount.resources.CreateAccountResources
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.userlib.usecase.CreateAccount
import hr.fer.nox.userlib.usecase.CreateAccountInfo
import io.reactivex.Completable

class CreateAccountPresenter(
    private val createAccountResources: CreateAccountResources,
    private val networkUtils: NetworkUtils,
    private val createAccount: CreateAccount
): BasePresenter<CreateAccountContract.View, CreateAccountViewState>(), CreateAccountContract.Presenter {

    override fun initialViewState(): CreateAccountViewState = CreateAccountViewState("", false)

    override fun createAccount(name: String, surname: String, email: String, password: String) {
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty()) {
            viewStateAction {
                errorMessage = createAccountResources.createAccountCredentialsEmptyErrorMessage()
                isLoading = false
            }

            return
        }

        runCommand(
            createAccountCommand(name, surname, email, password)
                .doOnComplete { dispatchRoutingAction(Router::showHome)  }
                .doOnError { t ->
                    viewStateAction {
                        errorMessage = t.message ?: ""
                        isLoading = false
                    }
                }
        )
    }

    private fun createAccountCommand(name: String, surname: String, email: String, password: String): Completable {
        viewStateAction {
            errorMessage = ""
            isLoading = true
        }

        return networkUtils.connectionTypeSingle().flatMapCompletable { connectionType: ConnectionType -> createAccountInternal(name, surname, email, password, connectionType) }
    }

    private fun createAccountInternal(name: String, surname: String, email: String, password: String, connectionType: ConnectionType): Completable =
        if (connectionType is NoConnection) {
            Completable.fromAction {
                viewStateAction {
                    isLoading = false
                    errorMessage = createAccountResources.noInternetConnectionErrorMessage()
                }
            }
        } else {
            createAccount.invoke(CreateAccountInfo(name, surname, email, password))
        }
}