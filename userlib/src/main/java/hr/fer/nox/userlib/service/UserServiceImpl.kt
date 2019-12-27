package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.ApiUserDetails
import hr.fer.nox.userlib.model.ApiUsersList
import io.reactivex.Flowable
import io.reactivex.Single

class UserServiceImpl(private val userApi: UserApi): UserService {


    override fun searchUsers(searchTerm: String): Single<ApiUsersList> =
        userApi.searchUsers(searchTerm)



    override fun getUserDetails(userId: String): Flowable<ApiUserDetails> {
        //return userApi.getUserDetails(userId)
        return Flowable.just(ApiUserDetails("id", "Karlo3", "Razumovic", "karlo.razumovic@gmail.com", "apache-helicopter", 99, 2))
    }
}