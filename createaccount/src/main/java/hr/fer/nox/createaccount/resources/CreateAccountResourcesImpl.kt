package hr.fer.nox.createaccount.resources

import hr.fer.nox.coreui.util.ResourceUtils
import hr.fer.nox.createaccount.R

class CreateAccountResourcesImpl(
    private val resourceUtils: ResourceUtils
): CreateAccountResources {

    override fun noInternetConnectionErrorMessage(): String = resourceUtils.getStringText(R.string.createaccount_no_connection_error_message)

    override fun createAccountCredentialsEmptyErrorMessage(): String = resourceUtils.getStringText(R.string.createaccount_credentials_empty_error_message)

    override fun invalidEmailErrorMessage(): String = resourceUtils.getStringText(R.string.createaccount_invalid_email)
}