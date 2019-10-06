package hr.fer.nox.login.resources

import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.login.R

class LoginResourcesImpl(
    private val resourceUtils: ResourceUtils
): LoginResources {

    override fun noInternetConnectionErrorMessage(): String = resourceUtils.getStringText(R.string.login_no_connection_error_message)

    override fun invalidUsernameAndPasswordErrorMessage(): String = resourceUtils.getStringText(R.string.login_invalid_username_and_password_error_message)

    override fun loginCredentialsEmptyErrorMessage(): String = resourceUtils.getStringText(R.string.login_credentials_empty_error_message)
}