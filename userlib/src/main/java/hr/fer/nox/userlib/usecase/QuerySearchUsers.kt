package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable


class QuerySearchUsers(
    private val userSource: UserSource
) : QueryUseCase<List<UserDetails>> {

    override fun invoke(): Flowable<List<UserDetails>> = userSource.querySearchedUser()
}
