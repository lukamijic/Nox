package hr.fer.nox.navigation.di

import hr.fer.nox.navigation.routing.RoutingActionDispatcher
import hr.fer.nox.navigation.routing.RoutingActionSource
import hr.fer.nox.navigation.routing.RoutingMediator
import hr.fer.nox.navigation.routing.RoutingMediatorImpl
import org.koin.dsl.module

val NavigationModule = module {

    single<RoutingMediator> { RoutingMediatorImpl()}
    single<RoutingActionDispatcher> { get<RoutingMediator>() }
    single<RoutingActionSource> { get<RoutingMediator>() }
}