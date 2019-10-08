package hr.fer.nox.home.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface HomeContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, HomeViewState> {

        fun showMovies()

        fun showExplore()

        fun showRecommendations()

        fun showProfile()
    }
}