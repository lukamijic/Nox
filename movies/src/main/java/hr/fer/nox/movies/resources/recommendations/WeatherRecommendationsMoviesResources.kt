package hr.fer.nox.movies.resources.recommendations

import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.resources.MoviesResources

class WeatherRecommendationsMoviesResources(
    private val resourceUtils: ResourceUtils
): MoviesResources {

    override fun getTitle(): String = resourceUtils.getStringText(R.string.movies_weather_recommendations_title)
}