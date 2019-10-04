package hr.fer.nox.navigation.routing

import hr.fer.nox.navigation.router.Router
import java.util.*

class RoutingMediatorImpl: RoutingMediator {

    private val queueLock = Any()
    private val routingActionQueue: Queue<(Router) -> Unit> = LinkedList<(Router) -> Unit>()

    private var routingActionConsumer: RoutingActionConsumer? = null

    override fun dispatch(routingAction: (Router) -> Unit) {
        synchronized(queueLock) {
            routingActionConsumer?.onRoutingAction(routingAction) ?: routingActionQueue.add(routingAction)
        }
    }

    override fun setActiveRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer) {
        this.routingActionConsumer?.let { throw IllegalStateException("Cannot set more than one RoutingActionConsumer") }
        synchronized(queueLock) {
            this.routingActionConsumer ?: flushRoutingActions(routingActionConsumer)
            this.routingActionConsumer = routingActionConsumer
        }
    }

    override fun removeRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer) {
        if (this.routingActionConsumer !== routingActionConsumer) {
            throw IllegalStateException("RoutingActionConsumer cannot unset another RoutingActionConsumer")
        }

        this.routingActionConsumer = null
    }

    private fun flushRoutingActions(routingActionConsumer: RoutingActionConsumer) {
        while (routingActionQueue.peek() != null) {
            routingActionConsumer.onRoutingAction(routingActionQueue.poll()!!)
        }
    }
}