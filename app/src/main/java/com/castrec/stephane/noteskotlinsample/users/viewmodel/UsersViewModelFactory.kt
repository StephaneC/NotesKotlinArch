package com.castrec.stephane.noteskotlinsample.users.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.castrec.stephane.noteskotlinsample.users.services.UsersDataContract
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by sca on 18/02/2018.
 */
class UsersViewModelFactory (private val repository: UsersDataContract.Repository, private val compositeDisposable: CompositeDisposable) :
        ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UsersViewModel(repository, compositeDisposable) as T
    }
}