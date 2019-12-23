package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.ApiUserDetails
import hr.fer.nox.userlib.model.ApiUsersList
import hr.fer.nox.userlib.model.UserDetails
import io.reactivex.Flowable
import io.reactivex.Single

interface UserService {

    fun searchUsers(searchTerm: String): Single<ApiUsersList>

    fun getUserDetails(userId: String) : Flowable<ApiUserDetails>
}