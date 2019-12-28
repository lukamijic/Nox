package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.userlib.model.AccessToken
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable

class QueryIsUserLoggedIn(
    private val userSource: UserSource
) : QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = userSource.getAccessToken().map { it != AccessToken.EMPTY }
}