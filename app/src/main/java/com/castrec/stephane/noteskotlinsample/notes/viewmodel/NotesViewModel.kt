package com.castrec.stephane.noteskotlinsample.notes.viewmodel

import android.arch.lifecycle.ViewModel
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import com.castrec.stephane.noteskotlinsample.notes.services.NotesDataContract
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 */
class NotesViewModel(private val repository: NotesDataContract.Repository,
                     private val compositeDisposable: CompositeDisposable) : ViewModel() {

    fun fetchNotes(): Flowable<List<Note>> {
        return repository.fetchNotes()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }

}