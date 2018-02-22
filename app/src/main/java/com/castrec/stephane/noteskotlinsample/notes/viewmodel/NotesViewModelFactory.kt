package com.castrec.stephane.noteskotlinsample.notes.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.castrec.stephane.noteskotlinsample.notes.services.NotesDataContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sca on 18/02/2018.
 */
class NotesViewModelFactory(private val repository: NotesDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NotesViewModel(repository, compositeDisposable) as T
    }
}