package hr.fer.nox.coreui.util

import io.reactivex.Flowable

interface KeyboardWatcher {

    fun initialize()

    fun destroy()

    fun keyboardListener(): Flowable<Boolean>
}