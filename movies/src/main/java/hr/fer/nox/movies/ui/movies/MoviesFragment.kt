package hr.fer.nox.movies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.movies.R
import hr.fer.nox.movies.model.MovieItemViewModel
import hr.fer.nox.movies.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_movies.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class MoviesFragment : BaseFragment<MoviesViewState>(), MoviesContract.View {

    companion object {

        const val TAG = "MoviesFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_movies

        private const val SCOPE_NAME_KEY = "scope_name"

        private const val COLUMN_COUNT = 3

        fun newInstance(scopeName: String): Fragment =
            MoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(SCOPE_NAME_KEY, scopeName)
                }
            }
    }

    private val presenter: MoviesContract.Presenter by scopedInject()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter(
            LayoutInflater.from(context)
        ) { movieItemViewModel: MovieItemViewModel -> presenter.showMovieDetails("") }


        movies_recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT, RecyclerView.VERTICAL, false)
            adapter = moviesAdapter
            OverScrollDecoratorHelper.setUpOverScroll(this, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
        }
    }

    override fun render(viewState: MoviesViewState) {
        moviesAdapter.submitList(viewState.moviesTemplateItemViewModel)
    }

    private fun getScopeNameFromBundle(): String = arguments?.getString(SCOPE_NAME_KEY) ?: throw IllegalArgumentException("Scope id not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = getScopeNameFromBundle()
    override fun getViewPresenter(): ViewPresenter<BaseView, MoviesViewState> = presenter as ViewPresenter<BaseView, MoviesViewState>
}