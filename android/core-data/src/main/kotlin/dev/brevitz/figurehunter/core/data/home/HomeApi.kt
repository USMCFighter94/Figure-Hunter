package dev.brevitz.figurehunter.core.data.home

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {
    @GET("api/v1/figure/all")
    fun getFigures(): Single<Response<List<FigureResponse>>>
}
