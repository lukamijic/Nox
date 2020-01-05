package hr.fer.nox.moviedetails.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface MovieDetailsContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, MovieDetailsViewState> {

        fun likeAction()
    }
}