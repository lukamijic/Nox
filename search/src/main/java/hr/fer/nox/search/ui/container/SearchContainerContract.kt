package hr.fer.nox.search.ui.container

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface SearchContainerContract {

    interface View: BaseView

    interface Presenter: ViewPresenter<View, SearchContainerViewState>
}