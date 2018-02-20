package com.castrec.stephane.noteskotlinsample.users.viewmodel

import android.arch.lifecycle.ViewModel
import com.castrec.stephane.noteskotlinsample.users.model.Token
import com.castrec.stephane.noteskotlinsample.users.services.UsersDataContract
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sca on 18/02/2018.
 */
class UsersViewModel(private val repository: UsersDataContract.Repository,
                     private val compositeDisposable: CompositeDisposable) : ViewModel() {

    fun signin(login: String, pwd: String): Flowable<Token> {
        return repository.signin(login, pwd);
    }

    override fun onCleared() {
        super.onCleared()
        //clear the disposables when the viewmodel is cleared
        compositeDisposable.clear()
    }

}