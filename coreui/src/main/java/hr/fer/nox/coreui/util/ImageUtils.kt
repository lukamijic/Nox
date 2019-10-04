package hr.fer.nox.coreui.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions

interface ImageUtils {

    fun loadInto(uri: String, imageView: ImageView)

    fun loadInto(bitmap: Bitmap?, imageView: ImageView, fallback: Drawable?)

    fun loadInto(uri: String, imageView: ImageView, requestOptions: RequestOptions)
}