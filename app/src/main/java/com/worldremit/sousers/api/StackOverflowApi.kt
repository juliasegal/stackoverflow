package com.worldremit.sousers.api

import com.worldremit.sousers.api.model.SoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface StackOverflowApi {
    @get:GET("2.2/users?pagesize=100&order=desc&sort=reputation&site=stackoverflow")
    val topUsers: Single<SoResponse?>

    @GET("2.2/users/{ids}?pagesize=1&site=stackoverflow")
    fun fetchUser(@Path("ids") id: Int): Single<SoResponse?>
}