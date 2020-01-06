package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.api.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class UserServiceImpl(private val userApi: UserApi) : UserService {

    override fun createAccount(
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ): Completable = Completable.fromSingle(userApi.signUp(ApiSignUpRequest(email, firstName, lastName, password)))

    override fun login(email: String, password: String): Single<ApiLoginResponse> =
        userApi.login(ApiLoginRequest(email, password))

    override fun searchUsers(searchTerm: String): Single<ApiUsersList> =
        userApi.searchUsers(searchTerm)


    override fun getUserDetails(userId: String): Flowable<ApiUserDetails> {
        if (userId.equals("ME")){
            return userApi.getMyUserDetails()
        } else {
            return userApi.getUserDetails(userId)
        }
        /*return Flowable.just(
            ApiUserDetails(
                "id",
                "Karlo3",
                "Razumovic",
                "karlo.razumovic@gmail.com",
                "apache-helicopter",
                99,
                2
            )
        ) */
    }

    override fun getAllUsers(): Flowable<List<ApiUserShort>> {
        return userApi.getAllUsers()
    }
}