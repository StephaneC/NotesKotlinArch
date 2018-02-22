package com.castrec.stephane.noteskotlinsample.chat.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.castrec.stephane.noteskotlinsample.chat.services.ChatsDataContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sca on 18/02/2018.
 */
class ChatsViewModelFactory(private val repository: ChatsDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatsViewModel(repository, compositeDisposable) as T
    }
}