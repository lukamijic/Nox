package hr.fer.nox.movies.ui.movies

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.movies.R
import kotlinx.android.synthetic.main.fragment_movies.*
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class MoviesFragment: BaseFragment<MoviesViewState>(), MoviesContract.View {

    companion object {

        const val TAG = "MoviesFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_movies

        private const val SCOPE_NAME_KEY = "scope_name"

        fun newInstance(scopeName: String): Fragment =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(SCOPE_NAME_KEY, scopeName)
                }
            }
    }


    private val presenter: MoviesContract.Presenter by inject()

    override fun render(viewState: MoviesViewState) {
        movies_title.text = viewState.title
    }

    private fun getScopeNameFromBundle(): String = arguments?.getString(SCOPE_NAME_KEY) ?: throw IllegalArgumentException("Scope id not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = getScopeNameFromBundle()
    override fun getViewPresenter(): ViewPresenter<BaseView, MoviesViewState> = presenter as ViewPresenter<BaseView, MoviesViewState>
}