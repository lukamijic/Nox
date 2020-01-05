package hr.fer.nox.permissions.di

import androidx.appcompat.app.AppCompatActivity
import hr.fer.nox.permissions.PermissionHandler
import hr.fer.nox.permissions.PermissionHandlerImpl
import hr.fer.nox.permissions.usecase.QueryLocationPermission
import org.koin.dsl.module

val PermissionsModule = module {

    single<PermissionHandler> {
        val appCompatActivity: AppCompatActivity = it[0]
        PermissionHandlerImpl(appCompatActivity)
    }

    single { QueryLocationPermission(get()) }
}