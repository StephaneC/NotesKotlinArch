package com.castrec.stephane.noteskotlinsample.notes.services

import android.arch.persistence.room.*
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 22/02/2018.
 */
@Dao
interface NotesDao {

    @Query("SELECT * from Notes")
    fun getNotes(): Flowable<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(notes: List<Note>)
}