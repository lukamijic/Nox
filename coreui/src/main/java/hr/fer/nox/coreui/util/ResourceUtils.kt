package hr.fer.nox.coreui.util

import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceUtils {

    fun getColor(@ColorRes color: Int): Int

    fun getDrawable(@DrawableRes drawableId: Int): Drawable?

    fun getStringText(@StringRes stringResId: Int): String

    fun getStringText(@StringRes stringResId: Int, arguments: Array<Any>): String

    fun getStatusBarHeight(): Int
}