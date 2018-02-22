package com.castrec.stephane.noteskotlinsample.users.services

/**
 * Created by sca on 19/02/2018.
 */
import com.castrec.stephane.noteskotlinsample.commons.model.Token
import io.reactivex.Flowable
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthenticationServices {
    @POST("/signin")
    fun signin(@Query("username") login: String, @Query("pwd")  pwd: String): Flowable<Token>

    @POST("/signup")
    fun signup(@Query("username") login: String, @Query("pwd") pwd: String, @Query("urlPhoto") photoUrl: String)
}