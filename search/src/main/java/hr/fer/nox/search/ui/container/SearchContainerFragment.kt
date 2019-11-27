package hr.fer.nox.search.ui.container

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.search.R
import hr.fer.nox.search.di.SEARCH_CONTAINER_VIEW_SCOPE
import hr.fer.nox.search.ui.container.adapter.SearchContainerPagerAdapter
import kotlinx.android.synthetic.main.fragment_search_container.*
import org.koin.android.ext.android.inject

class SearchContainerFragment: BaseFragment<SearchContainerViewState>(), SearchContainerContract.View {

    companion object {

        const val TAG = "SearchContainerFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_search_container

        fun newInstance(): Fragment = SearchContainerFragment()

    }

    private val presenter: SearchContainerContract.Presenter by scopedInject()
    private val resourceUtils: ResourceUtils by inject()

    private lateinit var searchContainerPagerAdapter: SearchContainerPagerAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        searchContainerPagerAdapter = SearchContainerPagerAdapter(resourceUtils, childFragmentManager)
        search_viewPager.adapter = searchContainerPagerAdapter
        search_tabLayout.setupWithViewPager(search_viewPager)
    }

    override fun render(viewState: SearchContainerViewState) {

    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = SEARCH_CONTAINER_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, SearchContainerViewState> = presenter as ViewPresenter<BaseView, SearchContainerViewState>
}