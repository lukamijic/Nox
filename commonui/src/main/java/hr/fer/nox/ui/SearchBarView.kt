package hr.fer.nox.ui

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.AbsoluteSizeSpan
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.jakewharton.rxbinding3.widget.editorActions
import com.jakewharton.rxbinding3.widget.textChanges
import hr.fer.nox.commonui.R
import hr.fer.nox.extensions.*
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_search_bar.view.*
import java.util.concurrent.TimeUnit

class SearchBarView : ConstraintLayout {

    companion object {
        private const val TAG = "SearchBarView"
        private const val SEARCH_THROTTLE_TIME = 1000L
    }

    var searchConsumer: (String) -> Unit = {}

    private var automaticSearch: Boolean = false
    private val disposables: CompositeDisposable = CompositeDisposable()

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)
    }

    fun setText(text: String) {
        searchBarEditText.setText(text, TextView.BufferType.EDITABLE)
    }

    fun setAutomaticSearchAction() {
        this.automaticSearch = true
        searchBarEditText.imeOptions = IME_ACTION_DONE
        searchBarIcon.setOnClickListener { searchBarEditText.setText("") }
    }

    fun setManualSearchAction() {
        this.automaticSearch = false
        searchBarEditText.imeOptions = IME_ACTION_SEARCH
        searchBarIcon.setOnClickListener { searchConsumer(searchBarEditText.text.toString()) }
    }

    private fun onSuccess(query: CharSequence) = searchConsumer.invoke(query.toString())

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        disposables.add(
                searchBarEditText.textChanges()
                        .filter { automaticSearch }
                        .debounce(SEARCH_THROTTLE_TIME, TimeUnit.MILLISECONDS)
                        .subscribe(this::onSuccess) { throwable -> Log.e(TAG, "Error in SearchBarView", throwable) }
        )

        disposables.add(
                searchBarEditText.editorActions()
                        .filter { it == IME_ACTION_SEARCH }
                        .subscribe(
                                { onSuccess(searchBarEditText.text) },
                                { throwable -> Log.e(TAG, "Error in SearchBarView", throwable) }
                        )
        )
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposables.dispose()
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.view_search_bar, this, true)

        attrs?.withTypedArray(context, R.styleable.SearchBarView) {
            withString(R.styleable.SearchBarView_searchText) { setText(it) }
            withColor(R.styleable.SearchBarView_searchTextColor, ContextCompat.getColor(context, R.color.text_color)) { searchBarEditText.setTextColor(it) }
            withIntDimension(R.styleable.SearchBarView_searchTextSize) { searchBarEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, it.toFloat()) }
            withResourceId(R.styleable.SearchBarView_searchFontFamily) { searchBarEditText.typeface = ResourcesCompat.getFont(context, it) }
            withString(R.styleable.SearchBarView_searchHint) { hint ->
                withIntDimension(R.styleable.SearchBarView_searchHintTextSize) {
                    searchBarEditText.hint = SpannableString(hint).apply { setSpan(AbsoluteSizeSpan(it), 0, hint.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE) }
                }
            }
            withColor(R.styleable.SearchBarView_searchHintColor, ContextCompat.getColor(context, R.color.text_color_gray)) { searchBarEditText.setHintTextColor(it) }
            withResourceId(R.styleable.SearchBarView_searchIcon) { searchBarIcon.setImageResource(it) }
            setBackgroundResource(R.drawable.view_search_bar_background)
        }
    }
}