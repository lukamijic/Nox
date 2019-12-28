package hr.fer.nox.app.container

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import hr.fer.nox.R
import hr.fer.nox.coreui.base.BaseActivity
import hr.fer.nox.navigation.routing.BackPropagatingFragment
import hr.fer.nox.navigation.routing.RoutingActionSource
import hr.fer.nox.userlib.usecase.QueryIsUserLoggedIn
import io.reactivex.disposables.Disposable
import org.koin.android.ext.android.inject

class ContainerActivity : BaseActivity() {

    private val routingActionsSource: RoutingActionSource by inject()
    private val isUserLoggedIn: QueryIsUserLoggedIn by inject()

    private lateinit var disposable: Disposable

    override fun getLayout(): Int = R.layout.activity_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        disposable = isUserLoggedIn().subscribe {isLoggedIn ->
            if (isLoggedIn) router.showHome() else router.showLogin()
        }
    }

    override fun onStart() {
        super.onStart()
        routingActionsSource.setActiveRoutingActionConsumer(this)
    }

    override fun onStop() {
        routingActionsSource.removeRoutingActionConsumer(this)
        disposable.dispose()
        super.onStop()
    }

    override fun onBackPressed() {
        val backPropagatingFragment = findBackPropagatingFragment(supportFragmentManager)
        backPropagatingFragment?.back() ?: super.onBackPressed()
    }

    private fun findBackPropagatingFragment(fragmentManager: FragmentManager): BackPropagatingFragment? {
        val fragment = fragmentManager.fragments.reversed().firstOrNull { it?.isVisible == true }
        return if (fragment is BackPropagatingFragment?) fragment else null
    }
}
