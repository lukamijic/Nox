package hr.fer.nox.permissions.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.permissions.PermissionHandler
import io.reactivex.Flowable

class QueryLocationPermission(
    private val permissionHandler: PermissionHandler
) : QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = permissionHandler.locationPermission()
}