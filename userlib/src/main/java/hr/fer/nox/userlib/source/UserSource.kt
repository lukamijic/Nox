package hr.fer.nox.userlib.source

import hr.fer.nox.userlib.model.AccessToken
import hr.fer.nox.userlib.model.UserDetails
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserSource {

    fun createAccount(email: String, password: String): Completable

    fun loginWithEmailAndPassword(email: String, password: String): Completable

    fun storeAccessToken(accessToken: String?): Completable

    fun getAccessToken(): Flowable<AccessToken>

    fun searchUsers(query: String): Completable

    fun getUserDetails(userId: String): Flowable<UserDetails>
}