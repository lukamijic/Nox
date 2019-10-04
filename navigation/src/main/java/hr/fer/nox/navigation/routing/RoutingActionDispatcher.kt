package hr.fer.nox.navigation.routing

import hr.fer.nox.navigation.router.Router

interface RoutingActionDispatcher {

    fun dispatch(routingAction: (Router) -> Unit)
}