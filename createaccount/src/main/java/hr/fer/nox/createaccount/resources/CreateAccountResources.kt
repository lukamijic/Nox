package hr.fer.nox.createaccount.resources

interface CreateAccountResources {

    fun noInternetConnectionErrorMessage(): String

    fun createAccountCredentialsEmptyErrorMessage(): String
}