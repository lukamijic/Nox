package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.api.ApiLoginResponse
import hr.fer.nox.userlib.model.api.ApiUserDetails
import hr.fer.nox.userlib.model.api.ApiUserShort
import hr.fer.nox.userlib.model.api.ApiUsersList
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface UserService {

    fun createAccount(
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ) : Completable

    fun login(email: String, password: String): Single<ApiLoginResponse>

    fun searchUsers(searchTerm: String): Single<ApiUsersList>

    fun getMyUserDetails(): Flowable<ApiUserDetails>

    fun getUserDetails(userId: String) : Flowable<ApiUserDetails>

    fun getAllUsers(): Flowable<List<ApiUserShort>>

    fun followUser(userId: String) : Completable

    fun unfollowUser(userId: String) : Completable
}
