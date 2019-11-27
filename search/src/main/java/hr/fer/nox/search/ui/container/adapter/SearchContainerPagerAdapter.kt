package hr.fer.nox.search.ui.container.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.search.R
import hr.fer.nox.search.ui.movies.SearchMoviesFragment

private const val TAB_COUNT = 2

class SearchContainerPagerAdapter(
    private val resourceUtils: ResourceUtils,
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence? =
        when(position) {
            0 -> resourceUtils.getStringText(R.string.search_movies)
            else -> resourceUtils.getStringText(R.string.search_users)
        }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> SearchMoviesFragment.newInstance()
            else -> SearchMoviesFragment.newInstance()
        }

    override fun getCount(): Int = TAB_COUNT
}