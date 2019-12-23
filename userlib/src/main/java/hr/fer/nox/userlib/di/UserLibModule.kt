package hr.fer.nox.userlib.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import hr.fer.nox.userlib.mapper.UserDetailsMapper
import hr.fer.nox.userlib.mapper.UserDetailsMapperImpl
import hr.fer.nox.userlib.mapper.UserMapper
import hr.fer.nox.userlib.mapper.UserMapperImpl
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.service.UserApi
import hr.fer.nox.userlib.service.UserService
import hr.fer.nox.userlib.service.UserServiceImpl
import hr.fer.nox.userlib.source.UserSource
import hr.fer.nox.userlib.source.UserSourceImpl
import hr.fer.nox.userlib.usecase.*
import org.koin.dsl.module
import retrofit2.Retrofit

val UserLibModule = module {

    single { FirebaseAuth.getInstance() }

    single { FirebaseDatabase.getInstance() }

    single<UserSource> { UserSourceImpl(get(), get(), get(), get(), get()) }

    single { CreateAccount(get()) }

    single { IsUserLoggedIn(get()) }

    single { LoginWithEmailAndPassword(get()) }

    single { FacebookLogin(get()) }

    single { GoogleLogin(get()) }

    single { QuerySearchUsers(get())}

    single { SearchUsers(get())}

    single { User(get()) }

    single<UserService> {
        UserServiceImpl(get())
    }

    single {
        get<Retrofit>().create(UserApi::class.java)
    }

    single<UserDetailsMapper> {
        UserDetailsMapperImpl()
    }

    single<UserMapper> {
        UserMapperImpl()
    }

    single { QueryUserDetails(get()) }
}