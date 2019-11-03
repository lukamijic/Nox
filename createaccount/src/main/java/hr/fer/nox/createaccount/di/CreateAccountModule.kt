package hr.fer.nox.createaccount.di

import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.createaccount.resources.CreateAccountResources
import hr.fer.nox.createaccount.resources.CreateAccountResourcesImpl
import hr.fer.nox.createaccount.ui.CreateAccountContract
import hr.fer.nox.createaccount.ui.CreateAccountPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val CreateAccountModule = module {

    scope(named(CREATE_ACCOUNT_VIEW_SCOPE)) {

        scoped {
            val presenter = CreateAccountPresenter(get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as CreateAccountContract.Presenter
        }

        scoped<CreateAccountResources> { CreateAccountResourcesImpl(get()) }
    }
}

const val CREATE_ACCOUNT_VIEW_SCOPE = "CreateAccount view scope"