package hr.fer.nox.navigation.routing

interface RoutingActionSource {

    fun setActiveRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)

    fun removeRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)
}