package hr.fer.nox.search.ui.container

import hr.fer.nox.coreui.base.BasePresenter

class SearchContainerPresenter : BasePresenter<SearchContainerContract.View, SearchContainerViewState>(), SearchContainerContract.Presenter {

    override fun initialViewState(): SearchContainerViewState = SearchContainerViewState()
}