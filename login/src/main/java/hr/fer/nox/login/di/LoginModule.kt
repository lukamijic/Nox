package hr.fer.nox.login.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.login.resources.LoginResources
import hr.fer.nox.login.resources.LoginResourcesImpl
import hr.fer.nox.login.ui.LoginContract
import hr.fer.nox.login.ui.LoginPresenter
import org.koin.dsl.module.module

val LoginModule = module {
    scope(LOGIN_VIEW_SCOPE) {
        val presenter = LoginPresenter(get(), get()).apply {
            mainThreadScheduler = get(name = MAIN_SCHEDULER)
            backgroundScheduler = get(name = BACKGROUND_SCHEDULER)
            routingActionsDispatcher = get()
            start()
        }
        presenter as LoginContract.Presenter
    }

    scope(LOGIN_VIEW_SCOPE) {
        LoginResourcesImpl(get()) as LoginResources
    }

}

const val LOGIN_VIEW_SCOPE = "LoginUser view scope"