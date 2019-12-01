package hr.fer.nox.userlib.source

import android.os.Bundle
import com.facebook.AccessToken
import com.facebook.GraphRequest
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.FirebaseDatabase
import durdinapps.rxfirebase2.RxFirebaseAuth
import durdinapps.rxfirebase2.RxFirebaseDatabase
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.util.EmptyGraphJSONObjectCallback
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException

class UserSourceImpl(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDatabase: FirebaseDatabase
) : UserSource {

    override fun createAccount(email: String, password: String): Flowable<String> =
        RxFirebaseAuth.createUserWithEmailAndPassword(firebaseAuth, email, password)
            .flatMapPublisher { Flowable.just(it.user?.uid ?: "") }

    override fun loginWithEmailAndPassword(email: String, password: String): Completable =
        RxFirebaseAuth.signInWithEmailAndPassword(firebaseAuth, email, password)
            .flatMapCompletable { Completable.fromAction {} }

    override fun isUserLoggedIn(): Flowable<Boolean> = Flowable.just(firebaseAuth.currentUser != null)

    override fun storeUserData(id: String, name: String, surName: String, email: String): Completable =
        RxFirebaseDatabase.setValue(firebaseDatabase.reference.child("users").child(id), User(id, name, surName, email))

    override fun facebookLogin(accessToken: AccessToken): Completable =
        RxFirebaseAuth.signInWithCredential(firebaseAuth, FacebookAuthProvider.getCredential(accessToken.token))
            .observeOn(Schedulers.newThread())
            .flatMapCompletable {
                val graphRequest = GraphRequest.newMeRequest(
                    accessToken,
                    EmptyGraphJSONObjectCallback()
                )

                graphRequest.parameters = Bundle().apply {
                    putString("fields", "first_name, last_name, email")
                }

                val userId = firebaseAuth.currentUser?.uid ?: throw RuntimeException("User is not logged in")
                graphRequest.executeAndWait().jsonObject.run {
                    storeUserData(userId, getString("first_name"), getString("last_name"), getString("email"))
                }
            }

    override fun googleLogin(googleSignInAccount: GoogleSignInAccount): Completable =
        RxFirebaseAuth.signInWithCredential(firebaseAuth, GoogleAuthProvider.getCredential(googleSignInAccount.idToken, null))
            .flatMapCompletable {
                val userId = firebaseAuth.currentUser?.uid ?: throw RuntimeException("User is not logged in")
                storeUserData(
                    userId,
                    googleSignInAccount.givenName ?: "",
                    googleSignInAccount.familyName ?: "",
                    googleSignInAccount.email ?: ""
                )
            }
}