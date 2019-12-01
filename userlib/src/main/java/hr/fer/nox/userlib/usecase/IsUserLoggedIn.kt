package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable

class IsUserLoggedIn(
    private val userSource: UserSource
) : QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = userSource.isUserLoggedIn()
}