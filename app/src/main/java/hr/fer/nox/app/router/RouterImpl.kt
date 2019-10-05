package hr.fer.nox.app.router

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import hr.fer.nox.R
import hr.fer.nox.app.util.inTransaction
import hr.fer.nox.navigation.router.Router
import hr.fer.nox.splash.ui.SplashFragment

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_CONTAINER_ID = R.id.main_activity_container

class RouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager
): Router {

    override fun showSplash() {
        fragmentManager.inTransaction { replace(MAIN_CONTAINER_ID, SplashFragment.newInstance(), SplashFragment.TAG) }
    }

    override fun goBack() = if (fragmentManager.backStackEntryCount == LAST_FRAGMENT) closeScreen() else fragmentManager.popBackStack()

    private fun closeScreen() = activity.finish()
}