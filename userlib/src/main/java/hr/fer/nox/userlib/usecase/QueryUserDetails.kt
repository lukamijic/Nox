package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCaseWithParameter
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable

class QueryUserDetails(
    private val userSource: UserSource
): QueryUseCaseWithParameter<String, UserDetails> {

    override fun invoke(param: String): Flowable<UserDetails> = userSource.getUserDetails(param)
}