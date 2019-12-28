package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.CommandUseCase
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class ClearAccessToken(
    private val userSource: UserSource
): CommandUseCase {

    override fun invoke(): Completable = userSource.storeAccessToken(null)
}