package hr.fer.nox.userlib.usecase

import com.facebook.AccessToken
import hr.fer.nox.core.usecase.CommandUseCaseWithParameter
import hr.fer.nox.userlib.source.UserSource
import io.reactivex.Completable

class FacebookLogin(
    private val userSource: UserSource
): CommandUseCaseWithParameter<AccessToken> {

    override fun invoke(parameter: AccessToken): Completable = userSource.facebookLogin(parameter)
}