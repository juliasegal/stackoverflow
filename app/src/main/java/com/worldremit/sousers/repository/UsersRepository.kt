package com.worldremit.sousers.repository

import com.worldremit.sousers.api.model.User
import io.reactivex.Single

interface UsersRepository {

    fun fetchTopUsers(): Single<List<User>>

    fun fetchUser(userId: Int): Single<User?>
}