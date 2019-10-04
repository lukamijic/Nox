package hr.fer.nox.coreui.util

import android.view.View

interface KeyboardUtils {

    fun showSoftKeyboard()

    fun hideSoftKeyboard(view: View?)

    fun isKeyboardShown(): Boolean
}