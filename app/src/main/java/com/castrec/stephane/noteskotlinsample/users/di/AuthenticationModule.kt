package com.castrec.stephane.noteskotlinsample.users.di

import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.users.fragments.SigninFragment
import com.castrec.stephane.noteskotlinsample.users.services.*
import com.castrec.stephane.noteskotlinsample.users.viewmodel.UsersViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * Created by sca on 20/02/2018.
 */
@Component(dependencies = [CoreComponent::class], modules = [AuthenticationModule::class])
@UsersScope
interface AuthenticationComponent {

    fun usersServices(): UsersServices
    fun scheduler(): Scheduler

    fun inject(fragment: SigninFragment)
}

@UsersScope
@Module
class AuthenticationModule {
    @Provides
    @UsersScope
    fun usersViewModelFactory(repository: UsersDataContract.Repository, compositeDisposable: CompositeDisposable): UsersViewModelFactory = UsersViewModelFactory(repository, compositeDisposable)

    /*Repository*/
    @Provides
    @UsersScope
    fun usersRepo(local: UsersDataContract.Local, remote: UsersDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): UsersDataContract.Repository = UsersRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @UsersScope
    fun remoteData(usersService: UsersServices): UsersDataContract.Remote = UsersRemoteData(usersService)

    @Provides
    @UsersScope
    fun localData(sharedPreferences: SharedPreferences): UsersDataContract.Local = UsersLocalData(sharedPreferences)

    @Provides
    @UsersScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @UsersScope
    fun postService(retrofit: Retrofit): UsersServices = retrofit.create(UsersServices::class.java)

}