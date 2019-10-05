package hr.fer.nox.splash.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.splash.R
import hr.fer.nox.splash.di.SPLASH_VIEW_SCOPE
import kotlinx.android.synthetic.main.fragment_splash.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import oxim.digital.rx2anim.RxAnimations.*

class SplashFragment : BaseFragment<SplashViewState>(), SplashContract.View {

    companion object {

        const val TAG = "SplashFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_splash

        fun newInstance(): Fragment = SplashFragment()

        private const val FADE_IN_DURATION = 2500
    }

    private val presenter: SplashContract.Presenter by inject(parameters = { parametersOf(FADE_IN_DURATION)} )

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        splash_logo.apply {
            alpha = 0f
            isInvisible = false
        }

        splash_brandName.apply {
            alpha = 0f
            isInvisible = false
        }

        addDisposable(
            animateTogether(
                fadeIn(splash_logo, FADE_IN_DURATION),
                fadeIn(splash_brandName, FADE_IN_DURATION)
            ).subscribe()
        )
    }

    override fun render(viewState: SplashViewState) {

    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = SPLASH_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, SplashViewState> = presenter as ViewPresenter<BaseView, SplashViewState>
}