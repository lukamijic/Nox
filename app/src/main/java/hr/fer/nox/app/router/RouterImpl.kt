package hr.fer.nox.app.router

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import hr.fer.nox.R
import hr.fer.nox.navigation.router.Router

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_CONTAINER_ID = R.id.main_activity_container

class RouterImpl(
    private val activity: Activity,
    private val fragmentManager: FragmentManager
): Router {

    override fun goBack() = if (fragmentManager.backStackEntryCount == LAST_FRAGMENT) closeScreen() else fragmentManager.popBackStack()

    private fun closeScreen() = activity.finish()
}