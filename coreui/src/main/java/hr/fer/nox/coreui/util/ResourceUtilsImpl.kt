package hr.fer.nox.coreui.util
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat

class ResourceUtilsImpl(
    private val resources: Resources
) : ResourceUtils {

    companion object {

        private const val DEFAULT_STATUS_BAR_HEIGHT = 76
        private const val ANDROID_PACKAGE_NAME = "android"
        private const val ID_STATUS_BAR_HEIGHT = "status_bar_height"
        private const val KEY_DIMEN = "dimen"
    }

    override fun getColor(color: Int): Int = ResourcesCompat.getColor(resources, color, null)

    override fun getDrawable(drawableId: Int): Drawable? = ResourcesCompat.getDrawable(resources, drawableId, null)

    override fun getStringText(stringResId: Int): String = resources.getString(stringResId)

    override fun getStringText(stringResId: Int, arguments: Array<Any>): String = resources.getString(stringResId, *arguments)

    override fun getStatusBarHeight(): Int {
        var result = DEFAULT_STATUS_BAR_HEIGHT
        val resourceId = resources.getIdentifier(ID_STATUS_BAR_HEIGHT, KEY_DIMEN, ANDROID_PACKAGE_NAME)
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}