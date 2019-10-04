package hr.fer.nox.core.di

import android.os.Looper
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.koin.dsl.module.module
import java.util.concurrent.TimeUnit

const val BACKGROUND_SCHEDULER = "BACKGROUND_SCHEDULER"
const val MAIN_SCHEDULER = "MAIN_SCHEDULER"

val ThreadingModule =  module {

    single(BACKGROUND_SCHEDULER) { Schedulers.io() }

    single<Scheduler> (MAIN_SCHEDULER) {
        OnRescheduleNotifyMainScheduler { throw IllegalStateException("RxChain already on MainThread!") }
    }

}

private class OnRescheduleNotifyMainScheduler(private inline val onRescheduleToMainThreadListener: () -> Unit) : Scheduler() {

    private val mainScheduler = AndroidSchedulers.from(Looper.getMainLooper(), true)

    override fun createWorker() = object : Worker() {

        private val worker = mainScheduler.createWorker()

        override fun schedule(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                onRescheduleToMainThreadListener()
            }

            return worker.schedule(run, delay, unit)
        }

        override fun dispose() = worker.dispose()

        override fun isDisposed() = worker.isDisposed
    }
}