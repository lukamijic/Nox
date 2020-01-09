package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.api.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {


    @GET("/users/search")
    fun searchUsers(@Query("query") searchTerm: String): Single<ApiUsersList>

    @GET("/user/{userId}")
    fun getUserDetails(@Path("userId") userId: String): Flowable<ApiUserDetails>

    @GET("/user/me")
    fun getMyUserDetails(): Flowable<ApiUserDetails>

    @POST("/auth/signup")
    fun signUp(@Body signUpRequest: ApiSignUpRequest): Single<ApiSignUpResponse>

    @POST("/auth/login")
    fun login(@Body loginRequest: ApiLoginRequest): Single<ApiLoginResponse>

    @GET("/user/all")
    fun getAllUsers():Flowable<List<ApiUserShort>>

    @GET("/likedUsers")
    fun getLikedUsers(): Flowable<List<ApiUserShort>>

    @POST("/likedUsers/{userId}")
    fun followUser(@Path("userId") userId: String) : Completable

    @DELETE("/likedUsers/{userId}")
    fun unfollowUser(@Path("userId") userId: String) : Completable
}