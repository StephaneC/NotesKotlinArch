package com.castrec.stephane.noteskotlinsample.notes.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import java.util.List

/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class NotesRemoteData(private val notesService: NotesServices): NotesDataContract.Remote {
    override fun postNote(note: String): Flowable<Note> {
        return notesService.postNote(note)
    }

    override fun checkNote(noteId: String, done: Boolean): Flowable<Note> {
        return notesService.checkNote(noteId, done)
    }

    override fun fetchNotes(): Flowable<List<Note>> {
        Log.d("KotlinNotes", "Users remote fetch")
        return notesService.fetchNotes();
    }
}