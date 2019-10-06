package hr.fer.nox.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import hr.fer.nox.commonui.R
import hr.fer.nox.extensions.*
import kotlinx.android.synthetic.main.view_navigation_item.view.*

class NavigationItemView: ConstraintLayout {

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    @DrawableRes private var activeIconRes: Int = -1

    @DrawableRes private var inactiveIconRes: Int = -1

    @ColorInt private var activeTextColor = ContextCompat.getColor(context, R.color.colorAccent)

    @ColorInt private var inactiveTextColor = ContextCompat.getColor(context, R.color.text_color_gray)

    fun activate() {
        navigationItemUnderline.visibility = View.VISIBLE
        navigationItemIcon.setImageResource(activeIconRes)
        navigationItemTitle.setTextColor(activeTextColor)
    }

    fun deactivate() {
        navigationItemUnderline.visibility = View.GONE
        navigationItemIcon.setImageResource(inactiveIconRes)
        navigationItemTitle.setTextColor(inactiveTextColor)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_navigation_item, this, true)

        attrs?.withTypedArray(context, R.styleable.NavigationItemView) {
            withColor(R.styleable.NavigationItemView_navigationItemTitleActiveTextColor, ContextCompat.getColor(context, R.color.colorAccent)) { activeTextColor = it }
            withColor(R.styleable.NavigationItemView_navigationItemTitleInactiveTextColor, ContextCompat.getColor(context, R.color.text_color_gray)) {
                inactiveTextColor = it
                navigationItemTitle.setTextColor(inactiveTextColor)
            }
            withIntDimension(R.styleable.NavigationItemView_navigationItemTitleTextSize) { navigationItemTitle.textSize = it.toFloat() }
            withString(R.styleable.NavigationItemView_navigationItemTitleText) { navigationItemTitle.text = it }

            withResourceId(R.styleable.NavigationItemView_navigationItemActiveIcon) { activeIconRes = it }
            withResourceId(R.styleable.NavigationItemView_navigationItemInactiveIcon) {
                inactiveIconRes = it
                navigationItemIcon.setImageResource(it)
            }

            withColor(R.styleable.NavigationItemView_navigationItemUnderlineColor, ContextCompat.getColor(context, R.color.colorAccent)) { navigationItemUnderline.setBackgroundColor(it) }
        }
    }
}