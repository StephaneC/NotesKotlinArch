package com.castrec.stephane.noteskotlinsample.notes.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class NotesLocalData(private val notesDb: NotesDB): NotesDataContract.Local {
    override fun saveNote(note: Note) {
        return notesDb.notesDao().upsert(note)
    }

    override fun fetchNotes(): Flowable<List<Note>> {
        Log.d("KotlinNotes", "Users Local fetch")
        return notesDb.notesDao().getNotes()
    }

    override fun saveNotes(notes: List<Note>) {
        Log.d("KotlinNotes", "Users Local save")
        notesDb.notesDao().upsertAll(notes)
    }


}