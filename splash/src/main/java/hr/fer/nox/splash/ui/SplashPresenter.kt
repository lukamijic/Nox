package hr.fer.nox.splash.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.userlib.usecase.QueryIsUserLoggedIn
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashPresenter(
    private val delayDuration: Int,
    private val isUserLoggedIn: QueryIsUserLoggedIn
): BasePresenter<SplashContract.View, SplashViewState>(), SplashContract.Presenter {

    override fun onStart() {
        runCommand(
            isUserLoggedIn()
                .firstOrError()
                .delay(delayDuration.toLong(), TimeUnit.MILLISECONDS)
                .flatMapCompletable {
                    Completable.fromAction {
                        if (it) {
                            dispatchRoutingAction(Router::showHome)
                        } else {
                            dispatchRoutingAction(Router::showLogin)
                        }
                    }
                }
        )
    }

    override fun initialViewState(): SplashViewState = SplashViewState()
}