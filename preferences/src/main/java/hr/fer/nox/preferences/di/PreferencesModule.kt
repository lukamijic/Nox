package hr.fer.nox.preferences.di

import hr.fer.nox.preferences.UserPreferences
import hr.fer.nox.preferences.UserPreferencesImpl
import org.koin.dsl.module

val PreferencesModule = module {


    single<UserPreferences> { UserPreferencesImpl(get()) }
}