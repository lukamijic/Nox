package hr.fer.nox.navigation.routing

import hr.fer.nox.navigation.router.Router

interface RoutingActionConsumer {

    fun onRoutingAction(routingAction: (Router) -> Unit)
}