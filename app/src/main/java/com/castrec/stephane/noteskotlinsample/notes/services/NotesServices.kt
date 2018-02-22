package com.castrec.stephane.noteskotlinsample.notes.services

/**
 * Created by sca on 19/02/2018.
 */
import android.provider.ContactsContract
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Header

interface NotesServices {
    @GET("/notes")
    fun fetchNotes(@Header("token") token: String): Flowable<List<Note>>
}