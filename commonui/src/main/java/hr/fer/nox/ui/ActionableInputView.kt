package hr.fer.nox.ui

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import hr.fer.nox.commonui.R
import io.reactivex.processors.PublishProcessor
import kotlinx.android.synthetic.main.view_actionable_input.view.*

class ActionableInputView : FrameLayout {

    private val focusSubject: PublishProcessor<Boolean> = PublishProcessor.create()

    private var typeface: Typeface? = null
    private var showActionItemIContent: Boolean = false
    private var savedState : String? = ""

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()
        val savedState = SavedState(superState)
        savedState.inputText = actionableInputEditText.text.toString()
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state is SavedState) {
            super.onRestoreInstanceState(state.superState)
            this.savedState = state.inputText
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_actionable_input, this, true)

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.ActionableInputView, defStyleAttr, 0)
        val inputTextSize = attributes.getDimensionPixelSize(R.styleable.ActionableInputView_inputTextSize, 32)
        val errorTextColor = attributes.getColor(R.styleable.ActionableInputView_errorTextColor, Color.RED)
        val borderColor = attributes.getColor(R.styleable.ActionableInputView_borderColor, Color.BLACK)
        val hintText = attributes.getString(R.styleable.ActionableInputView_hintText)
        val actionIconResourceId = attributes.getResourceId(R.styleable.ActionableInputView_actionIcon, R.drawable.ic_close)
        @FontRes val fontResource = attributes.getResourceId(R.styleable.ActionableInputView_fontFamily, R.font.roboto_black)
        showActionItemIContent = attributes.getBoolean(R.styleable.ActionableInputView_actionIconIfContent, false)
        typeface = ResourcesCompat.getFont(context, fontResource)
        attributes.recycle()

        with(actionableInputEditText) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, inputTextSize.toFloat())
            typeface = typeface
            hint = hintText
            setOnFocusChangeListener { _, hasFocus -> if (hasFocus) inputAcquiredFocus() else inputLostFocus() }
        }

        actionableInputErrorText.setTextColor(errorTextColor)
        actionableInputBottomBorder.setBackgroundColor(borderColor)
        actionableInputActionButton.setImageResource(actionIconResourceId)
    }

    private fun inputAcquiredFocus() {
        focusSubject.onNext(true)
        actionableInputBottomBorder.visibility = View.VISIBLE
    }

    private fun inputLostFocus() {
        focusSubject.onNext(false)
        if (TextUtils.isEmpty(actionableInputEditText.text)) {
            actionableInputBottomBorder.visibility = View.GONE
        }
    }

    fun setButtonClickListener(onClickListener: View.OnClickListener) {
        actionableInputActionButton.setOnClickListener(onClickListener)
    }

    fun setInputType(inputType: Int) {
        actionableInputEditText.inputType = inputType
        actionableInputEditText.typeface = typeface
    }

    fun showActionButton() {
        actionableInputActionButton.visibility = View.VISIBLE
    }

    fun hideActionButton() {
        if (!showActionItemIContent) {
            actionableInputActionButton.visibility = View.GONE
        }
    }

    fun focusChange(): PublishProcessor<Boolean> {
        return focusSubject
    }

    fun setActionButtonDrawableRes(@DrawableRes drawableRes: Int) = actionableInputActionButton.setImageResource(drawableRes)

    fun setText(text: String) = actionableInputEditText.setText(text)

    fun getText(): String = actionableInputEditText.text.toString()

    fun setErrorText(text: String) {
        actionableInputErrorText.text = text
    }

    fun getErrorText(): String = actionableInputErrorText.text.toString()

    fun setErrorTextVisible(visible: Boolean) {
        actionableInputErrorText.visibility = if (visible) View.VISIBLE else View.GONE
    }

    fun restoreTextState() {
        savedState?.run { actionableInputEditText.setText(this) }
    }

    fun setCursorAtEnd() = actionableInputEditText.setSelection(actionableInputEditText.text.length)

    internal class SavedState : View.BaseSavedState {

        var inputText: String? = null

        constructor(parcel: Parcel) : super(parcel) {
            this.inputText = parcel.readString()
        }

        constructor(superState: Parcelable) : super(superState)

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            super.writeToParcel(parcel, flags)
            parcel.writeString(inputText)
        }

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }
}