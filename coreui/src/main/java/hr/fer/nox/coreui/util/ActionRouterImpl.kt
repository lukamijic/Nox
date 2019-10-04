package hr.fer.nox.coreui.util

import io.reactivex.disposables.Disposable
import io.reactivex.processors.PublishProcessor
import java.util.concurrent.TimeUnit

private const val DEFAULT_WINDOW_DURATION_MILIS = 250L

class ActionRouterImpl: ActionRouter {

    private var router: PublishProcessor<() -> Unit>? = null
    private var routerDisposable: Disposable? = null

    private var windowDuration = DEFAULT_WINDOW_DURATION_MILIS
    private var timeUnit = TimeUnit.MILLISECONDS

    init {
        initRouter()
        listenRouter()
    }

    override fun setTiming(windowDuration: Long, timeUnit: TimeUnit) {
        dispose()
        this.windowDuration = windowDuration
        this.timeUnit = timeUnit
        listenRouter()
    }

    override fun throttle(action: () -> Unit) {
        try {
            router?.onNext(action)
        } catch (throwable: Throwable) {
            dispose()
            initRouter()
            listenRouter()
        }
    }


    private fun initRouter() {
        router = PublishProcessor.create()
    }

    private fun listenRouter() {
        routerDisposable = router?.throttleFirst(windowDuration, timeUnit)
            ?.subscribe { it() }
    }

    private fun dispose() {
        routerDisposable?.dispose()
        routerDisposable = null
    }
}