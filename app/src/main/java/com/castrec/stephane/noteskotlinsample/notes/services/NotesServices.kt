package com.castrec.stephane.noteskotlinsample.notes.services

/**
 * Created by sca on 19/02/2018.
 */
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import retrofit2.http.*
import java.util.List

interface NotesServices {
    @GET("/notes")
    fun fetchNotes(): Flowable<List<Note>>

    @POST("/notes")
    fun postNote(@Query("note") note: String): Flowable<Note>

    @FormUrlEncoded
    @POST("/notes/{note_id}")
    fun checkNote(@Path(value = "note_id", encoded = true) noteId: String, @Field("done") done: Boolean): Flowable<Note>
}