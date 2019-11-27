package hr.fer.nox.moviedetails.resources

import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.moviedetails.R

class MovieDetailsResourcesImpl(
    private val resourceUtils: ResourceUtils
): MovieDetailsResources {

    override fun getMovieDuration(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes - (hours * 60)
        return resourceUtils.getStringText(R.string.moviedetails_duration_format, arrayOf(hours, remainingMinutes))
    }
}