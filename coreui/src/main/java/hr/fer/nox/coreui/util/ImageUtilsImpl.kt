package hr.fer.nox.coreui.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageUtilsImpl(
    private val context: Context
): ImageUtils {

    override fun loadInto(uri: String, imageView: ImageView) {
        Glide.with(context)
            .load(uri)
            .into(imageView)
    }

    override fun loadInto(bitmap: Bitmap?, imageView: ImageView, fallback: Drawable?) {
        Glide.with(context)
            .load(bitmap)
            .apply(RequestOptions().fallback(fallback))
            .into(imageView)
    }

    override fun loadInto(uri: String, imageView: ImageView, requestOptions: RequestOptions) {
        Glide.with(context)
            .load(uri)
            .apply(requestOptions)
            .into(imageView)
    }
}