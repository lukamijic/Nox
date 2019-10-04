package hr.fer.nox.coreui.util

import android.app.Activity
import android.view.View
import android.view.ViewTreeObserver
import android.view.Window
import android.view.WindowManager
import io.reactivex.Flowable
import io.reactivex.processors.PublishProcessor
import java.lang.ref.WeakReference

class KeyboardWatcherImpl(activity: Activity, resourceUtils: ResourceUtils) : KeyboardWatcher {

    private val activityRef: WeakReference<Activity> = WeakReference(activity)
    private val keyboardShownSubject: PublishProcessor<Boolean> = PublishProcessor.create()
    private val rootViewRef: WeakReference<View> = WeakReference(activity.findViewById(Window.ID_ANDROID_CONTENT))
    private val statusBarHeight: Int = resourceUtils.getStatusBarHeight()

    private var viewTreeObserverListener: ViewTreeObserver.OnGlobalLayoutListener? = null

    override fun initialize() {
        if (hasAdjustResizeInputMode()) {
            viewTreeObserverListener = GlobalLayoutListener()
            rootViewRef.get()?.viewTreeObserver?.addOnGlobalLayoutListener(viewTreeObserverListener)
        } else {
            throw IllegalArgumentException("Activity should have windowSoftInputMode=adjustResize to make KeyboardWatcher working.")
        }
    }

    override fun destroy() {
        val rootView = rootViewRef.get()
        rootView?.viewTreeObserver?.removeOnGlobalLayoutListener(viewTreeObserverListener)
    }

    override fun keyboardListener(): Flowable<Boolean> {
        return keyboardShownSubject
    }

    private fun hasAdjustResizeInputMode(): Boolean {
        val activity = activityRef.get()
        return if (activity != null) {
            activity.window.attributes.softInputMode and WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE != 0
        } else false
    }

    private inner class GlobalLayoutListener : ViewTreeObserver.OnGlobalLayoutListener {

        private var initialValue: Int = 0
        private var isKeyboardShown: Boolean = false

        override fun onGlobalLayout() {
            val height: Int = rootViewRef.get()?.height ?: 0

            val diff = Math.abs(initialValue - height)
            if (initialValue == 0 || diff == statusBarHeight) { // This is fix when screen changes height because status bar is becoming visible.
                initialValue = height
            } else {
                if (initialValue > height) {
                    if (!isKeyboardShown) {
                        isKeyboardShown = true
                        keyboardShownSubject.onNext(true)
                    }
                } else {
                    if (isKeyboardShown) {
                        isKeyboardShown = false
                        keyboardShownSubject.onNext(false)
                    }
                }
            }
        }
    }
}