package hr.fer.nox.movies.ui.container

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface MoviesContainerContract {

    interface View: BaseView

    interface Presenter: ViewPresenter<View, MoviesContainerViewState>
}