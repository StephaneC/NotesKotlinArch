package com.castrec.stephane.noteskotlinsample.users.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.authentication.fragments.SigninFragment
import com.castrec.stephane.noteskotlinsample.commons.Constants
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.users.fragments.UsersFragment
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
@Component(dependencies = [CoreComponent::class], modules = [UsersModule::class])
@UsersScope
interface UsersComponent {

    fun usersServices(): UsersServices
    fun scheduler(): Scheduler

    fun inject(fragment: UsersFragment)
}

@UsersScope
@Module
class UsersModule {
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
    fun localData(notesDB: NotesDB): UsersDataContract.Local = UsersLocalData(notesDB)

    @Provides
    @UsersScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @UsersScope
    fun postService(retrofit: Retrofit): UsersServices = retrofit.create(UsersServices::class.java)

    /*Parent providers to dependents*/
    @Provides
    @UsersScope
    fun notesDb(context: Context): NotesDB = Room.databaseBuilder(context, NotesDB::class.java, Constants.Posts.DB_NAME).build()


}