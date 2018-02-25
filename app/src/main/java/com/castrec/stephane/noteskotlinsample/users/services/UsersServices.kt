package com.castrec.stephane.noteskotlinsample.users.services

/**
 * Created by sca on 19/02/2018.
 */
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import java.util.List

interface UsersServices {
    @GET("/users")
    fun fetchUsers(): Flowable<List<User>>
}