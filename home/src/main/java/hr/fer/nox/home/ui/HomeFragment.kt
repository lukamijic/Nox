package hr.fer.nox.home.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.home.R
import hr.fer.nox.home.di.HOME_VIEW_SCOPE
import hr.fer.nox.ui.NavigationItemView
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment: BaseFragment<HomeViewState>(), HomeContract.View {

    companion object {

        const val TAG = "HomeFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_home

        fun newInstance(): Fragment = HomeFragment()

        private const val MOVIES = 0
        private const val EXPLORE = 1
        private const val PROFILE = 2
        private const val SETTINGS = 3
    }

    private val presenter: HomeContract.Presenter by inject()
    private val bottomNavigationItemMap by lazy {
        mapOf<Int, NavigationItemView>(
            MOVIES to home_moviesNavigationItem,
            EXPLORE to home_exploreNavigationItem,
            PROFILE to home_profileNavigationItem,
            SETTINGS to home_settingsNavigationItem
        )
    }

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        super.initialiseView(view, savedInstanceState)

        home_moviesNavigationItem.activate()
        bottomNavigationItemMap.entries.forEach { e ->
            e.value.setOnClickListener {
                activateBottomNavigationItem(e.key)
            }
        }
    }

    override fun render(viewState: HomeViewState) {

    }

    private fun activateBottomNavigationItem(id: Int) {
        bottomNavigationItemMap.entries.forEach {
            if (id != it.key ) {
                it.value.deactivate()
            } else {
                it.value.activate()
            }
        }
    }


    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = HOME_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, HomeViewState> = presenter as ViewPresenter<BaseView, HomeViewState>
}