package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable


class QuerySearchUsers(
    private val userSource: UserSource
): QueryUseCase<List<User>> {

    override fun invoke(): Flowable<List<User>> = Flowable.just(listOf(User("id", "Karlo", "Razumovic", "karlo.razumovic@gmail.com")))
    // TODO: implement this
}