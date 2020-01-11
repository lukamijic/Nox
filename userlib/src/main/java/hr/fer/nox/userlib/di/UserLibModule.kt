package hr.fer.nox.userlib.di

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

    single<UserSource> { UserSourceImpl(get(), get(), get()) }

    single { CreateAccount(get()) }

    single { QueryIsUserLoggedIn(get()) }

    single { LoginWithEmailAndPassword(get()) }

    single { QuerySearchUsers(get())}

    single { SearchUsers(get())}

    single { User(get()) }

    single { ClearAccessToken(get()) }

    single { StoreAccessToken(get()) }

    single<UserService> { UserServiceImpl(get()) }

    single { get<Retrofit>().create(UserApi::class.java) }

    single<UserMapper> { UserMapperImpl() }

    single { GetAllUsers(get()) }

    single { QueryUserDetails(get())}

    single { QueryMyUserDetails(get()) }
}
