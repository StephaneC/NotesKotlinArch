package com.castrec.stephane.noteskotlinsample.users.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.castrec.stephane.noteskotlinsample.users.services.AuthenticationDataContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sca on 18/02/2018.
 */
class AuthenticationViewModelFactory(private val repository: AuthenticationDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthenticationViewModel(repository, compositeDisposable) as T
    }
}