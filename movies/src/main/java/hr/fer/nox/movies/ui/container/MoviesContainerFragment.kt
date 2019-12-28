package hr.fer.nox.movies.ui.container

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.di.MOVIES_CONTAINER_VIEW_SCOPE
import hr.fer.nox.movies.ui.container.adapter.MoviesContainerPagerAdapter
import hr.fer.nox.movies.ui.container.adapter.MoviesRecommendationsContainerPagerAdapter
import kotlinx.android.synthetic.main.fragment_movies_container.*
import org.koin.android.ext.android.inject

class MoviesContainerFragment: BaseFragment<MoviesContainerViewState>(), MoviesContainerContract.View {

    companion object {

        const val TAG = "MoviesContainerFragment"

        private const val VIEW_TYPE_KEY = "view_type"
        private const val MOVIES_VIEW_TYPE = "movies"
        private const val RECOMMENDATIONS_VIEW_TYPE = "recommendations"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_movies_container

        fun movies(): Fragment = newInstance().apply {
            arguments = Bundle().apply { putString(VIEW_TYPE_KEY, MOVIES_VIEW_TYPE) }
        }

        fun recommendations(): Fragment = newInstance().apply {
            arguments = Bundle().apply { putString(VIEW_TYPE_KEY, RECOMMENDATIONS_VIEW_TYPE) }
        }

        private fun newInstance(): Fragment = MoviesContainerFragment()
    }

    private val presenter: MoviesContainerContract.Presenter by scopedInject()
    private val resourceUtils: ResourceUtils by inject()

    private lateinit var moviesContainerPagerAdapter: FragmentPagerAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        moviesContainerPagerAdapter = if (getViewTypeFromBundle() == MOVIES_VIEW_TYPE) {
            MoviesContainerPagerAdapter(resourceUtils, childFragmentManager)
        } else {
            MoviesRecommendationsContainerPagerAdapter(resourceUtils, childFragmentManager)
        }
        movies_viewPager.adapter = moviesContainerPagerAdapter
        movies_tabLayout.setupWithViewPager(movies_viewPager)
    }

    override fun render(viewState: MoviesContainerViewState) {

    }

    private fun getViewTypeFromBundle(): String = arguments?.getString(VIEW_TYPE_KEY) ?: throw IllegalArgumentException("view type not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = MOVIES_CONTAINER_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, MoviesContainerViewState> = presenter as ViewPresenter<BaseView, MoviesContainerViewState>
}