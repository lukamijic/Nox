package hr.fer.nox.movies.ui.movies

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface MoviesContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, MoviesViewState> {

        fun showMovieDetails(movieId: Int)
    }
}