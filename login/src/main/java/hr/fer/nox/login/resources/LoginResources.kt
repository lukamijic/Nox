package hr.fer.nox.login.resources

interface LoginResources {

    fun noInternetConnectionErrorMessage(): String

    fun invalidUsernameAndPasswordErrorMessage(): String

    fun loginCredentialsEmptyErrorMessage(): String
}