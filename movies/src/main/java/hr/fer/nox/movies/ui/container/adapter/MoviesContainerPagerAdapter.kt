package hr.fer.nox.movies.ui.container.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.di.COMING_SOON_MOVIES_VIEW_SCOPE
import hr.fer.nox.movies.di.NEW_RELEASES_MOVIES_VIEW_SCOPE
import hr.fer.nox.movies.di.POPULAR_MOVIES_VIEW_SCOPE
import hr.fer.nox.movies.ui.movies.MoviesFragment

private const val TAB_COUNT = 1

class MoviesContainerPagerAdapter(
    private val resourceUtils: ResourceUtils,
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence? =
        when(position) {
            0 -> resourceUtils.getStringText(R.string.movies_popular_title)
            1 -> resourceUtils.getStringText(R.string.movies_new_releases_title)
            else -> resourceUtils.getStringText(R.string.movies_coming_soon_title)
        }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment.newInstance(POPULAR_MOVIES_VIEW_SCOPE)
            1 -> MoviesFragment.newInstance(NEW_RELEASES_MOVIES_VIEW_SCOPE)
            else -> MoviesFragment.newInstance(COMING_SOON_MOVIES_VIEW_SCOPE)
        }

    override fun getCount(): Int = TAB_COUNT
}