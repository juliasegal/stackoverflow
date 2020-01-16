package com.worldremit.sousers

import android.app.Application
import com.google.gson.GsonBuilder
import com.worldremit.sousers.repository.UsersRepository
import com.worldremit.sousers.repository.UsersRepositoryApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    private fun createHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        client.readTimeout(5 * 60, TimeUnit.SECONDS)

        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        client.addInterceptor(logging)
        return client.build()
    }


    val retrofit: Retrofit = Retrofit.Builder()
            .client(createHttpClient())
            .baseUrl("https://api.stackexchange.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    val usersRepository: UsersRepository = UsersRepositoryApi(retrofit)
}