package hr.fer.nox.permissions

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor

class PermissionHandlerImpl (
    private val permissionActivity: AppCompatActivity
): PermissionHandler {

    private val locationProcessor: BehaviorProcessor<Boolean> = BehaviorProcessor.createDefault(isLocationPermissionGranted())

    override fun locationPermission(): Flowable<Boolean> = locationProcessor

    override fun requestLocationPermission() {
        if (!isLocationPermissionGranted()) {
            ActivityCompat.requestPermissions(
                permissionActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                PermissionHandler.LOCATION_REQUEST_CODE)
        }
    }

    override fun locationPermissionGranted() {
        locationProcessor.onNext(true)
    }

    private fun isLocationPermissionGranted(): Boolean = ContextCompat.checkSelfPermission(permissionActivity, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
}