package hr.fer.nox.userlib.source

import hr.fer.nox.preferences.AccessToken
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.model.UserDetails
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserSource {

    fun createAccount(email: String, firstName: String, lastName: String, password: String): Completable

    fun loginWithEmailAndPassword(email: String, password: String): Completable

    fun storeAccessToken(accessToken: String?): Completable

    fun getAccessToken(): Flowable<AccessToken>

    fun searchUsers(query: String): Completable

    fun getMyUserDetails(): Flowable<UserDetails>

    fun getUserDetails(userId: String): Flowable<UserDetails>

    fun getAllUsers(): Flowable<List<User>>
}
