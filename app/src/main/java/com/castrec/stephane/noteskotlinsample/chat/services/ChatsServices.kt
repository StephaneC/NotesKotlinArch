package com.castrec.stephane.noteskotlinsample.chat.services

/**
 * Created by sca on 19/02/2018.
 */
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.List
interface ChatsServices {
    @GET("/messages")
    fun fetchChats(): Flowable<List<Chat>>

    @POST("/messages")
    fun addMessage(@Query("message") message : String): Flowable<List<Chat>>

}