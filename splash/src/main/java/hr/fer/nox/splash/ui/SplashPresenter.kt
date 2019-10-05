package hr.fer.nox.splash.ui

import hr.fer.nox.coreui.base.BasePresenter
import io.reactivex.Completable
import java.util.concurrent.TimeUnit

class SplashPresenter(
    private val delayDuration: Int
): BasePresenter<SplashContract.View, SplashViewState>(), SplashContract.Presenter {

    override fun onStart() {
        runCommand(Completable.fromAction{ }
            .delay(delayDuration.toLong(), TimeUnit.MILLISECONDS)
            .doOnComplete { showLogin() }
        )
    }

    override fun initialViewState(): SplashViewState = SplashViewState()

    override fun showLogin() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}