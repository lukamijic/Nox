package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class LoginWithEmailAndPassword(
    private val userSource: UserSource
) : CommandUseCaseWithParameter<LoginWithEmailAndPasswordInfo> {

    override fun invoke(parameter: LoginWithEmailAndPasswordInfo): Completable =
        userSource.loginWithEmailAndPassword(
            parameter.email,
            parameter.password
        )

}

data class LoginWithEmailAndPasswordInfo(
    val email: String,
    val password: String
)