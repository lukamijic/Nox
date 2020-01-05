package hr.fer.nox.location

import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import hr.fer.nox.location.model.Location

class LocationProviderImpl(
    private val locationManager: LocationManager
): LocationProvider, LocationListener {

    override fun getLocation(): Location {
        val isNetEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (isNetEnabled) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0.0f, this)
            val loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            return loc?.run { Location(latitude.toFloat(), longitude.toFloat()) } ?: Location.EMPTY
        }

        return Location.EMPTY
    }

    override fun onLocationChanged(location: android.location.Location?) {

    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {

    }

    override fun onProviderEnabled(provider: String?) {

    }

    override fun onProviderDisabled(provider: String?) {

    }
}