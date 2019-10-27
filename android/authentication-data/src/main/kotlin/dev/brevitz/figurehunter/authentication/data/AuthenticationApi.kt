package dev.brevitz.figurehunter.authentication.data

import dev.brevitz.figurehunter.authentication.domain.LoginData
import dev.brevitz.figurehunter.authentication.domain.RegisterData
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("api/v1/login")
    fun login(@Body data: LoginData): Single<Response<TokenResponse>>

    @POST("api/v1/register")
    fun register(@Body data: RegisterData): Single<Response<TokenResponse>>
}
