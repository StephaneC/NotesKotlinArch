package com.castrec.stephane.noteskotlinsample.users.services

/**
 * Created by sca on 19/02/2018.
 */
import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface UsersServices {
    @POST("/signin")
    fun signin(@Query("username") login: String, @Query("pwd")  pwd: String): Flowable<Token>

    @POST("/signup")
    fun signup(@Query("username") login: String, @Query("pwd") pwd: String, @Query("urlPhoto") photoUrl: String)

    @GET("/users/")
    fun fetchUsers(@Header("token") token: String): Flowable<List<User>>
}