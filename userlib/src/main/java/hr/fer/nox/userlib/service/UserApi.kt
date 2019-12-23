package hr.fer.nox.userlib.service

import hr.fer.nox.userlib.model.ApiUsersList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {


    @GET("/some_fake_ass_path")
    fun searchUsers(@Query("query") searchTerm: String): Single<ApiUsersList>
}