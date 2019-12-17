package hr.fer.nox.userlib.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.source.UserSource
import hr.fer.nox.userlib.source.UserSourceImpl
import hr.fer.nox.userlib.usecase.*
import org.koin.dsl.module

val UserLibModule = module {

    single { FirebaseAuth.getInstance() }

    single { FirebaseDatabase.getInstance() }

    single<UserSource> { UserSourceImpl(get(), get()) }

    single { CreateAccount(get()) }

    single { IsUserLoggedIn(get()) }

    single { LoginWithEmailAndPassword(get()) }

    single { FacebookLogin(get()) }

    single { GoogleLogin(get()) }

    single { QuerySearchUsers(get())}

    single { SearchUsers(get())}

    single { User(get()) }
}