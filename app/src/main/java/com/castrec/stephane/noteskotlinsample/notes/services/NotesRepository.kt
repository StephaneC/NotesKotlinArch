package com.castrec.stephane.noteskotlinsample.notes.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.disposables.CompositeDisposable
import java.util.List


/**
 * Created by sca on 19/02/2018.
 *
 * In charge of managing data.
 * - Fetch from api and stor into DB
 */
class NotesRepository(
        private val local: NotesDataContract.Local,
        private val remote: NotesDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
): NotesDataContract.Repository {

    override fun fetchNotes(): Flowable<List<Note>> {
        return Flowable.create({ emitter: FlowableEmitter<List<Note>> ->
            //first send stuff from local
            Log.d("KotlinNotes", "Loading notes")
            local.fetchNotes().subscribe({
                notes ->
                Log.d("KotlinNotes", "Loaded notes from local")
                emitter.onNext(notes)
                //then fecth from remote
                remote.fetchNotes().subscribe(
                        {notes ->
                            Log.d("KotlinNotes", "Loaded notes from remote")
                            emitter.onNext(notes)
                            local.saveNotes(notes)
                            emitter.onComplete()
                        },
                        {error -> emitter.onError(error)}
                )
            })
        }, BackpressureStrategy.BUFFER)
    }

}