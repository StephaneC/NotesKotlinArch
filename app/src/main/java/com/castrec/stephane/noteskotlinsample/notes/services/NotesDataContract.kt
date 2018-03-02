package com.castrec.stephane.noteskotlinsample.notes.services

import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 *
 * This class describe every interactions we can do with data
 */
interface NotesDataContract {
    /** call to db. */
    interface Repository {
        //Subject pattern - Will use Flowable for now
        //val notesFetchOutcome: PublishSubject<List<User>>
        //val tokenFetchOutcome: PublishSubject<Token>
        fun fetchNotes():Flowable<List<Note>>
        fun checkNote(noteId: String, done: Boolean) : Flowable<Note>
        fun postNote(note: String): Flowable<Note>

    }

    /** call to **/
    interface Local {
        fun fetchNotes(): Flowable<List<Note>>
        fun saveNotes(notes: List<Note>)
        fun saveNote(note: Note)

    }

    /** call to api. */
    interface Remote {
        fun fetchNotes(): Flowable<List<Note>>
        fun checkNote(noteId: String, done: Boolean) : Flowable<Note>
        fun postNote(note: String): Flowable<Note>
    }
}