package hr.fer.nox.coreui.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.navigation.routing.RoutingActionConsumer
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

abstract class BaseActivity : AppCompatActivity(), RoutingActionConsumer {

    protected val router: Router by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
    }

    override fun onRoutingAction(routingAction: (Router) -> Unit) = routingAction(router)

    @LayoutRes
    abstract fun getLayout(): Int
}