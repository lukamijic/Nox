package hr.fer.nox.userlib.source

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import io.reactivex.Completable
import io.reactivex.Flowable

interface UserSource {

    fun createAccount(email: String, password: String): Flowable<String>

    fun loginWithEmailAndPassword(email: String, password: String): Completable

    fun isUserLoggedIn(): Flowable<Boolean>

    fun storeUserData(id: String, name: String, surName: String, email: String): Completable

    fun facebookLogin(accessToken: AccessToken): Completable

    fun googleLogin(googleSignInAccount: GoogleSignInAccount): Completable
}