package hr.fer.nox.coreui.util

import android.view.View
import android.view.inputmethod.InputMethodManager

class KeyboardUtilsImpl(private val inputMethodManager: InputMethodManager) : KeyboardUtils {

    override fun hideSoftKeyboard(view: View?) {
        view?.run { inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0) }
    }

    override fun showSoftKeyboard() {
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    override fun isKeyboardShown(): Boolean = inputMethodManager.isAcceptingText
}