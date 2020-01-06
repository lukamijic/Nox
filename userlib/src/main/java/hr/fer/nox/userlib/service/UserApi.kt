package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.api.*
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.*

interface UserApi {


    @GET("/some_fake_ass_path")
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
}