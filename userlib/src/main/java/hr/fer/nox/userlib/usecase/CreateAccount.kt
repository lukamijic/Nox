package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class CreateAccount(
    private val userSource: UserSource
) : CommandUseCaseWithParameter<CreateAccountInfo> {

    override fun invoke(parameter: CreateAccountInfo): Completable =
        with(parameter) {
            userSource.createAccount(email, name, surname, password)
        }
}

data class CreateAccountInfo(
    val name: String,
    val surname: String,
    val email: String,
    val password: String
)