package hr.fer.nox.core.networking

interface ConnectivityManagerWrapper {

    fun isConnectedToNetwork(): Boolean

    fun getNetworkData(): ConnectionType

    data class NetworkData(val hasInternetConnection: Boolean, val isWifiConnection: Boolean, val isMobileConnection: Boolean)
}