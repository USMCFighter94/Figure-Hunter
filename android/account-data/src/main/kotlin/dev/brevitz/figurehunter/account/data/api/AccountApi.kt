package dev.brevitz.figurehunter.account.data.api

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountApi {
    @GET("api/v1/user/{id}")
    fun getUser(@Path("id") id: Int): Single<Response<UserResponse>>
}
