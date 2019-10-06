package hr.fer.nox.core.networking

import android.net.ConnectivityManager

class ConnectivityManagerWrapperImpl(private val connectivityManager: ConnectivityManager) : ConnectivityManagerWrapper {

    override fun isConnectedToNetwork(): Boolean {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    override fun getNetworkData(): ConnectionType {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        val hasInternetConnection = activeNetworkInfo != null && activeNetworkInfo.isConnected
        val isMobileConnection = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_MOBILE
        val isWifiConnection = activeNetworkInfo != null && activeNetworkInfo.type == ConnectivityManager.TYPE_WIFI

        return if (hasInternetConnection) {
            when {
                isWifiConnection -> Wifi
                isMobileConnection -> Cellular
                else -> NoConnection
            }
        } else {
            NoConnection
        }
    }
}