package com.castrec.stephane.noteskotlinsample.chat.services

/**
 * Created by sca on 19/02/2018.
 */
import android.provider.ContactsContract
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ChatsServices {
    @GET("/messages")
    fun fetchChats(@Header("token") token: String): Flowable<List<Chat>>

    @POST("/messages")
    fun addMessage(@Header("token") token: String, @Query("message") message : String): Flowable<List<Chat>>

}