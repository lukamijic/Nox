package hr.fer.nox.movies.resources

import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.movies.R

class ComingSoonMoviesResources(
    private val resourceUtils: ResourceUtils
): MoviesResources {

    override fun getTitle(): String = resourceUtils.getStringText(R.string.movies_coming_soon_title)
}