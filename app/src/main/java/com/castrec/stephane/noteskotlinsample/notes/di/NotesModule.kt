package com.castrec.stephane.noteskotlinsample.notes.di

import android.arch.persistence.room.Room
import android.content.Context
import com.castrec.stephane.noteskotlinsample.commons.Constants
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.notes.services.*
import com.castrec.stephane.noteskotlinsample.notes.viewmodel.NotesViewModelFactory
import com.castrec.stephane.noteskotlinsample.notes.di.NotesScope
import com.castrec.stephane.noteskotlinsample.notes.fragments.NotesFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * Created by sca on 20/02/2018.
 */
@Component(dependencies = [CoreComponent::class], modules = [NotesModule::class])
@NotesScope
interface NotesComponent {

    fun notesServices(): NotesServices
    fun scheduler(): Scheduler

    fun inject(fragment: NotesFragment)
}

@NotesScope
@Module
class NotesModule {
    @Provides
    @NotesScope
    fun notesViewModelFactory(repository: NotesDataContract.Repository, compositeDisposable: CompositeDisposable): NotesViewModelFactory = NotesViewModelFactory(repository, compositeDisposable)

    /*Repository*/
    @Provides
    @NotesScope
    fun notesRepo(local: NotesDataContract.Local, remote: NotesDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): NotesDataContract.Repository = NotesRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @NotesScope
    fun remoteData(session: Session, notesService: NotesServices): NotesDataContract.Remote = NotesRemoteData(notesService, session)

    @Provides
    @NotesScope
    fun localData(notesDB: NotesDB): NotesDataContract.Local = NotesLocalData(notesDB)

    @Provides
    @NotesScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @NotesScope
    fun postService(retrofit: Retrofit): NotesServices = retrofit.create(NotesServices::class.java)

    /*Parent providers to dependents*/
    @Provides
    @NotesScope
    fun notesDb(context: Context): NotesDB = Room.databaseBuilder(context, NotesDB::class.java, Constants.Posts.DB_NAME).build()


}