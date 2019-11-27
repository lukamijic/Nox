package hr.fer.nox.search.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.search.R
import hr.fer.nox.search.di.SEARCH_MOVIES_VIEW_SCOPE
import hr.fer.nox.search.model.MovieItemViewModel
import hr.fer.nox.search.ui.movies.adapter.MoviesAdapter
import kotlinx.android.synthetic.main.fragment_search_movies.*
import org.koin.android.ext.android.inject

class SearchMoviesFragment: BaseFragment<SearchMoviesViewState>(), SearchMoviesContract.View {

    companion object {

        const val TAG = "SearchMoviesFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_search_movies

        private const val COLUMN_COUNT = 3

        fun newInstance(): Fragment = SearchMoviesFragment()

    }

    private val presenter: SearchMoviesContract.Presenter by scopedInject()
    private val imageUtils: ImageUtils by inject()

    private lateinit var moviesAdapter: MoviesAdapter

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter(
            LayoutInflater.from(context),
            imageUtils
        ) { movieItemViewModel: MovieItemViewModel -> presenter.showMovieDetails(movieItemViewModel.movieId) }

        searchmovies_recyclerView.apply {
            layoutManager = GridLayoutManager(context, COLUMN_COUNT, RecyclerView.VERTICAL, false)
            adapter = moviesAdapter
        }

        searchmovies_searchBar.searchConsumer = { searchTerm -> presenter.searchMovies(searchTerm)}
        searchmovies_searchBar.setAutomaticSearchAction()
    }

    override fun render(viewState: SearchMoviesViewState) {
        searchmovies_progressView.isVisible = viewState.isLoading
        searchmovies_noMoviesFound.isVisible = viewState.movies.isEmpty()
        moviesAdapter.submitList(viewState.movies)
    }

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = SEARCH_MOVIES_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, SearchMoviesViewState> = presenter as ViewPresenter<BaseView, SearchMoviesViewState>
}