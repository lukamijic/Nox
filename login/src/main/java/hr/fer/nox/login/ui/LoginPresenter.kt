package hr.fer.nox.login.ui

import hr.fer.nox.core.networking.ConnectionType
import hr.fer.nox.core.networking.NetworkUtils
import hr.fer.nox.core.networking.NoConnection
import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.login.resources.LoginResources
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.userlib.usecase.LoginWithEmailAndPassword
import hr.fer.nox.userlib.usecase.LoginWithEmailAndPasswordInfo
import hr.fer.nox.userlib.usecase.QueryIsUserLoggedIn
import hr.fer.nox.userlib.usecase.StoreAccessToken
import io.reactivex.Completable

class LoginPresenter(
    private val loginResources: LoginResources,
    private val networkUtils: NetworkUtils,
    private val loginWithEmailAndPassword: LoginWithEmailAndPassword,
    private val storeAccessToken: StoreAccessToken,
    private val queryIsUserLoggedIn: QueryIsUserLoggedIn
): BasePresenter<LoginContract.View, LoginViewState>(), LoginContract.Presenter {

    override fun initialViewState(): LoginViewState = LoginViewState()

    override fun onStart() {
        addDisposable(
            queryIsUserLoggedIn()
                .filter { it }
                .doOnNext { dispatchRoutingAction(Router::showHome) }
                .subscribe()
        )
    }

    override fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            viewStateAction { this.errorMessage = loginResources.loginCredentialsEmptyErrorMessage() }
            return
        }

        runCommand(
            loginCommand(email, password)
                .doOnError {
                    viewStateAction {
                        isLoading = false
                        errorMessage = it.message ?:""
                    }
                }
                .doOnComplete {
                    dispatchRoutingAction(Router::showHome)
                    // TODO: get logged use info
                }
        )
    }

    override fun showCreateAccount() {
        dispatchRoutingAction(Router::showCreateAccount)
    }

    override fun storeAccessToken(accessToken: String) {
        runCommand(storeAccessToken.invoke(accessToken))
    }

    private fun loginCommand(email: String, password: String): Completable {
        viewStateAction {
            isLoading = true
            errorMessage = ""
        }

        return networkUtils.connectionTypeSingle().flatMapCompletable { connectionType: ConnectionType -> login(email, password, connectionType) }
    }

    private fun login(email: String, password: String, connectionType: ConnectionType): Completable =
        if (connectionType is NoConnection) {
            Completable.fromAction {
                viewStateAction {
                    isLoading = false
                    errorMessage = loginResources.noInternetConnectionErrorMessage()
                }
            }
        } else {
            loginWithEmailAndPassword(LoginWithEmailAndPasswordInfo(email, password))
        }
}