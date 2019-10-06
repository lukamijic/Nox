package hr.fer.nox.core.networking

import io.reactivex.Flowable
import io.reactivex.Single

interface NetworkUtils {

    fun hasInternetAccess(): Flowable<Boolean>

    fun hasInternetAccessSingle(): Single<Boolean>

    fun connectionType(): Flowable<ConnectionType>

    fun connectionTypeSingle(): Single<ConnectionType>

    fun isThrowableNetworkException(throwable: Throwable): Boolean
}
