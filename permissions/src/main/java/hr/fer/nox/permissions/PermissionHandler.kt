package hr.fer.nox.permissions

import io.reactivex.Flowable

interface PermissionHandler {

    companion object {

        const val LOCATION_REQUEST_CODE = 1
    }

    fun locationPermission(): Flowable<Boolean>

    fun requestLocationPermission()

    fun locationPermissionGranted()
}