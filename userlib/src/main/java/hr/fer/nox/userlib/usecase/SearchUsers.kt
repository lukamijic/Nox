package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class SearchUsers(
    private val userSource: UserSource
) : CommandUseCaseWithParameter<String> {

    override fun invoke(parameter: String): Completable = Completable.complete()
    // TODO: implement this
}