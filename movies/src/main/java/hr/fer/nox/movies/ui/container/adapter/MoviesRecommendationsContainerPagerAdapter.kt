package hr.fer.nox.movies.ui.container.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.di.*
import hr.fer.nox.movies.ui.movies.MoviesFragment

private const val TAB_COUNT = 2

class MoviesRecommendationsContainerPagerAdapter(
    private val resourceUtils: ResourceUtils,
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getPageTitle(position: Int): CharSequence? =
        when(position) {
            0 -> resourceUtils.getStringText(R.string.movies_user_recommendations_title)
            else -> resourceUtils.getStringText(R.string.movies_weather_recommendations_title)
        }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MoviesFragment.newInstance(USER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE)
            else -> MoviesFragment.newInstance(WEATHER_RECOMMENDATIONS_MOVIES_VIEW_SCOPE)
        }

    override fun getCount(): Int = TAB_COUNT
}