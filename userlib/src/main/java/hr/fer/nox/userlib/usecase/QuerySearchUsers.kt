package hr.fer.nox.userlib.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Flowable


class QuerySearchUsers(
    private val userSource: UserSource
) : QueryUseCase<List<User>> {

    //override fun invoke() : Flowable<List<User>> = userSource.searchUsers()
    override fun invoke(): Flowable<List<User>> {
        return Flowable.just(
            listOf(
                User("id", "Karlo2", "Razumovic", "karlo.razumovic@gmail.com"),
                User("id2", "Štef", "Štefanović", "štef.štefanovic@gmail.com")
            )
        )
    }
    // TODO: implement this
}