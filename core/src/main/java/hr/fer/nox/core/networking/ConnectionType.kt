package hr.fer.nox.core.networking

sealed class ConnectionType

object NoConnection : ConnectionType()
object Cellular : ConnectionType()
object Wifi : ConnectionType()