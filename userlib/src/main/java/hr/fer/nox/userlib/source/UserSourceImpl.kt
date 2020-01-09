package hr.fer.nox.userlib.source

import hr.fer.nox.userlib.mapper.UserMapper
import hr.fer.nox.preferences.AccessToken
import hr.fer.nox.userlib.model.User
import hr.fer.nox.userlib.model.UserDetails
import hr.fer.nox.preferences.UserPreferences
import hr.fer.nox.userlib.service.UserService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.PublishProcessor

class UserSourceImpl(
    private val userService: UserService,
    private val userMapper: UserMapper,
    private val userPreferences: UserPreferences
) : UserSource {

    private val searchUsersPublishProcessor: PublishProcessor<List<User>> =
        PublishProcessor.create()
    private val accessTokenBehaviorProcessor: BehaviorProcessor<AccessToken> =
        BehaviorProcessor.createDefault(userPreferences.getAccessToken())

    override fun createAccount(
        email: String,
        firstName: String,
        lastName: String,
        password: String
    ) : Completable = userService.createAccount(email, firstName, lastName, password)

    override fun loginWithEmailAndPassword(email: String, password: String): Completable =
        userService.login(email, password)
            .flatMapCompletable { storeAccessToken(it.accessToken) }

    override fun storeAccessToken(accessToken: String?): Completable =
        Completable
            .fromAction { userPreferences.setAccessToken(accessToken) }
            .andThen { accessTokenBehaviorProcessor.onNext(userPreferences.getAccessToken()) }

    override fun getAccessToken(): Flowable<AccessToken> = accessTokenBehaviorProcessor

    override fun searchUsers(query: String): Completable =
        userService
            .searchUsers(query)
            .map { userMapper.map(it.results) }
            .flatMapCompletable { users ->
                Completable.fromAction {
                    searchUsersPublishProcessor.onNext(
                        users
                    )
                }
            }

    override fun getUserDetails(userId: String): Flowable<UserDetails> =
        userService.getUserDetails(userId).map { userMapper.map(it) }

    override fun getAllUsers(): Flowable<List<User>> {
       return userService.getAllUsers().map{userMapper.map(it)}

    }
}