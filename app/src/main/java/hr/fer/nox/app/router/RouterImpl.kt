package hr.fer.nox.app.router

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import hr.fer.nox.R
import hr.fer.nox.app.util.inTransaction
import hr.fer.nox.home.ui.HomeFragment
import hr.fer.nox.login.ui.LoginFragment
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

    override fun showLogin() {
        fragmentManager.inTransaction {
            applyFadeInFadoOutAnimation()
            replace(MAIN_CONTAINER_ID, LoginFragment.newInstance(), LoginFragment.TAG)
        }
    }

    override fun showHome() {
        fragmentManager.inTransaction {
            applyFadeInFadoOutAnimation()
            replace(MAIN_CONTAINER_ID, HomeFragment.newInstance(), HomeFragment.TAG)
        }
    }

    override fun goBack() = if (fragmentManager.backStackEntryCount == LAST_FRAGMENT) closeScreen() else fragmentManager.popBackStack()

    private fun closeScreen() = activity.finish()

    private fun FragmentTransaction.applyFadeInFadoOutAnimation() {
        setCustomAnimations(R.anim.fragment_fade_in, R.anim.fragment_fade_out)
    }

    fun FragmentTransaction.applySlideInSlideOutAnimation() {
        setCustomAnimations(R.anim.fragment_right_enter, R.anim.fragment_left_exit, R.anim.fragment_left_enter, R.anim.fragment_right_exit)
    }
}