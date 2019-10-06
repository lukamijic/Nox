package hr.fer.nox.core.networking

import android.content.Context
import android.net.ConnectivityManager
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.InternetObservingSettings
import com.github.pwittchen.reactivenetwork.library.rx2.internet.observing.strategy.SocketInternetObservingStrategy
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLException


class NetworkUtilsImpl(private val context: Context, private val connectivityManagerWrapper: ConnectivityManagerWrapper) :
    NetworkUtils {


    companion object {
        private const val TIMEOUT_IN_SEC = 1L

        private const val INTERNET_CHECK_URL = "www.google.com"
    }

    private val settings: InternetObservingSettings = InternetObservingSettings.builder()
            .host(INTERNET_CHECK_URL)
            .strategy(SocketInternetObservingStrategy())
            .build()

    override fun isThrowableNetworkException(throwable: Throwable): Boolean =
            throwable is SSLException || throwable is UnknownHostException || throwable is SocketException

    override fun hasInternetAccess(): Flowable<Boolean> =
            ReactiveNetwork
                    .observeNetworkConnectivity(context)
                    .debounce(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                    .flatMapSingle { ReactiveNetwork.checkInternetConnectivity(settings) }
                    .toFlowable(BackpressureStrategy.LATEST)

    override fun hasInternetAccessSingle(): Single<Boolean> = Single.fromCallable { connectivityManagerWrapper.isConnectedToNetwork() }

    override fun connectionType(): Flowable<ConnectionType> =
            ReactiveNetwork
                    .observeNetworkConnectivity(context)
                    .debounce(TIMEOUT_IN_SEC, TimeUnit.SECONDS)
                    .toFlowable(BackpressureStrategy.LATEST)
                    .map { networkConnectivity ->
                        mapToConnectionType(networkConnectivity.type())
                    }

    override fun connectionTypeSingle(): Single<ConnectionType> = Single.fromCallable { connectivityManagerWrapper.getNetworkData() }

    private fun mapToConnectionType(connectionType: Int): ConnectionType =
            when (connectionType) {
                ConnectivityManager.TYPE_WIFI -> Wifi
                ConnectivityManager.TYPE_MOBILE -> Cellular
                else -> NoConnection
            }
}