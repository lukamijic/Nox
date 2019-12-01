package hr.fer.nox.login.di

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import hr.fer.nox.core.di.BACKGROUND_SCHEDULER
import hr.fer.nox.core.di.MAIN_SCHEDULER
import hr.fer.nox.login.resources.LoginResources
import hr.fer.nox.login.resources.LoginResourcesImpl
import hr.fer.nox.login.ui.LoginContract
import hr.fer.nox.login.ui.LoginPresenter
import org.koin.core.qualifier.named
import org.koin.dsl.module

val LoginModule = module {

    scope(named(LOGIN_VIEW_SCOPE)) {

        scoped {
            val presenter = LoginPresenter(get(), get(), get(), get(), get()).apply {
                mainThreadScheduler = get(named(MAIN_SCHEDULER))
                backgroundScheduler = get(named(BACKGROUND_SCHEDULER))
                routingActionsDispatcher = get()
                start()
            }
            presenter as LoginContract.Presenter
        }

        scoped {
            LoginResourcesImpl(get()) as LoginResources
        }

        scoped {
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("779913819288-b7imrj7fq86gsfrbgtg4gbsqaqqafdh3.apps.googleusercontent.com")
                .requestEmail()
                .build()
        }

        scoped {
            val context: Context = it[0]
            GoogleSignIn.getClient(context, get())
        }
    }
}

const val LOGIN_VIEW_SCOPE = "LoginUser view scope"