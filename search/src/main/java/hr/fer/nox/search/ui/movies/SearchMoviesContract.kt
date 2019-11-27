package hr.fer.nox.search.ui.movies

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface SearchMoviesContract {

    interface View: BaseView

    interface Presenter: ViewPresenter<View, SearchMoviesViewState> {

        fun searchMovies(searchTerm: String)

        fun showMovieDetails(movieId: Int)
    }
}