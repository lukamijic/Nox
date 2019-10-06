package hr.fer.nox.extensions

import android.content.res.TypedArray

inline fun TypedArray.withResourceId(index: Int, block: (Int) -> Unit) {
    getResourceId(index, -1).let {
        if (it != -1) {
            block(it)
        }
    }
}

inline fun TypedArray.withIntDimension(index: Int, defaultValue: Int = 0, block: (Int) -> Unit) {
    block(getDimension(index, defaultValue.toFloat()).toInt())
}

inline fun TypedArray.withInt(index: Int, invalidValue: Int = -1, block: (Int) -> Unit) {
    getInt(index, invalidValue).let {
        if (it != invalidValue) {
            block(it)
        }
    }
}

inline fun TypedArray.withInterestingBoolean(index: Int, interestingValue: Boolean, block: () -> Unit) {
    getBoolean(index, !interestingValue).let {
        if (it == interestingValue) {
            block()
        }
    }
}

inline fun TypedArray.withColor(index: Int, defaultValue: Int = -1, block: (Int) -> Unit) {
    getColor(index, defaultValue).let {
        block(it)
    }
}

inline fun TypedArray.withString(index: Int, block: (String) -> Unit) {
    block(getString(index).orEmpty())
}