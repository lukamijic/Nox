package hr.fer.nox.userlib.usecase

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class GoogleLogin(
    private val userSource: UserSource
): CommandUseCaseWithParameter<GoogleSignInAccount> {

    override fun invoke(parameter: GoogleSignInAccount): Completable = userSource.googleLogin(parameter)
}