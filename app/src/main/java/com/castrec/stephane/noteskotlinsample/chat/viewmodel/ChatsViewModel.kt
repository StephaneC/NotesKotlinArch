package com.castrec.stephane.noteskotlinsample.chat.viewmodel

import android.arch.lifecycle.ViewModel
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import com.castrec.stephane.noteskotlinsample.chat.services.ChatsDataContract
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 */
class ChatsViewModel(private val repository: ChatsDataContract.Repository,
                     private val compositeDisposable: CompositeDisposable) : ViewModel() {

    fun fetchChats(): Flowable<List<Chat>> {
        return repository.fetchChats()
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }

}