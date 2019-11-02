package hr.fer.nox.movies.ui.container

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.di.MOVIES_CONTAINER_VIEW_SCOPE
import hr.fer.nox.movies.ui.container.adapter.MoviesContainerPagerAdapter
import kotlinx.android.synthetic.main.fragment_movies_container.*
import org.koin.android.ext.android.inject

class MoviesContainerFragment: BaseFragment<MoviesContainerViewState>(), MoviesContainerContract.View {

    companion object {

        const val TAG = "MoviesContainerFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_movies_container

        fun newInstance(): Fragment = MoviesContainerFragment()

    }

    private val presenter: MoviesContainerContract.Presenter by scopedInject()
    private val resourceUtils: ResourceUtils by inject()

    private lateinit var moviesContainerPagerAdapter: MoviesContainerPagerAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        moviesContainerPagerAdapter = MoviesContainerPagerAdapter(resourceUtils, childFragmentManager)
        movies_viewPager.adapter = moviesContainerPagerAdapter
        movies_tabLayout.setupWithViewPager(movies_viewPager)
    }

    override fun render(viewState: MoviesContainerViewState) {

    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = MOVIES_CONTAINER_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, MoviesContainerViewState> = presenter as ViewPresenter<BaseView, MoviesContainerViewState>
}