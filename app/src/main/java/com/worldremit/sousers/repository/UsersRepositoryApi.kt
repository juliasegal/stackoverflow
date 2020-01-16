package com.worldremit.sousers.repository

import com.worldremit.sousers.api.StackOverflowApi
import com.worldremit.sousers.api.model.User
import io.reactivex.Single
import retrofit2.Retrofit
import java.util.stream.Collectors.toList

class UsersRepositoryApi(retrofit: Retrofit) : UsersRepository {

    private var api: StackOverflowApi = retrofit.create(StackOverflowApi::class.java)

    override fun fetchTopUsers(): Single<List<User>> {
        return api.topUsers.map { response -> response.users }
    }

    override fun fetchUser(userId: Int): Single<User?> {
        return api.fetchUser(userId).map { response ->
            if (response.users.size > 0) {
                response.users[0]
            } else {
                null
            }
        }
    }
}