package hr.fer.nox.splash.ui

import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter

interface SplashContract {

    interface View : BaseView

    interface Presenter : ViewPresenter<View, SplashViewState>
}