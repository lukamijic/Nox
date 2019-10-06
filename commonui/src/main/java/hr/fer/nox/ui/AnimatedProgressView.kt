package hr.fer.nox.ui

import android.content.Context
import android.graphics.Color
import android.graphics.ColorFilter
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.SimpleColorFilter
import com.airbnb.lottie.model.KeyPath
import com.airbnb.lottie.value.LottieValueCallback
import hr.fer.nox.commonui.R
import hr.fer.nox.extensions.withColor
import hr.fer.nox.extensions.withTypedArray
import kotlinx.android.synthetic.main.view_animated_progress.view.*

class AnimatedProgressView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_animated_progress, this, true)
        setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryDark))
        alpha = 0.8f
    }
}