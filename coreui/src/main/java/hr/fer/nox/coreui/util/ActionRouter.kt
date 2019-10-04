package hr.fer.nox.coreui.util

import java.util.concurrent.TimeUnit

interface ActionRouter {

    fun setTiming(windowDuration: Long, timeUnit: TimeUnit)

    fun throttle(action: () -> Unit)
}