package hr.fer.nox.splash.ui

import hr.fer.nox.coreui.base.BasePresenter
import hr.fer.nox.navigation.router.Router
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashPresenter(
    private val delayDuration: Int
): BasePresenter<SplashContract.View, SplashViewState>(), SplashContract.Presenter {

    override fun onStart() {
        runCommand(Completable.fromAction{ }
            .delay(delayDuration.toLong(), TimeUnit.MILLISECONDS)
            .doOnComplete { dispatchRoutingAction(Router::showLogin) }
        )
    }

    override fun initialViewState(): SplashViewState = SplashViewState()
}