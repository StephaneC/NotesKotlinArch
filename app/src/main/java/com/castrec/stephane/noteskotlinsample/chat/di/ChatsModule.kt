package com.castrec.stephane.noteskotlinsample.chat.di

import android.arch.persistence.room.Room
import android.content.Context
import com.castrec.stephane.noteskotlinsample.commons.Constants
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.chat.services.*
import com.castrec.stephane.noteskotlinsample.chat.viewmodel.ChatsViewModelFactory
import com.castrec.stephane.noteskotlinsample.users.di.ChatsScope
import com.castrec.stephane.noteskotlinsample.users.fragments.ChatsFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * Created by sca on 20/02/2018.
 */
@Component(dependencies = [CoreComponent::class], modules = [ChatsModule::class])
@ChatsScope
interface ChatsComponent {

    fun notesServices(): ChatsServices
    fun scheduler(): Scheduler

    fun inject(fragment: ChatsFragment)
}

@ChatsScope
@Module
class ChatsModule {
    @Provides
    @ChatsScope
    fun notesViewModelFactory(repository: ChatsDataContract.Repository, compositeDisposable: CompositeDisposable): ChatsViewModelFactory = ChatsViewModelFactory(repository, compositeDisposable)

    /*Repository*/
    @Provides
    @ChatsScope
    fun notesRepo(local: ChatsDataContract.Local, remote: ChatsDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): ChatsDataContract.Repository = ChatsRepository(local, remote, scheduler, compositeDisposable)

    @Provides
    @ChatsScope
    fun remoteData(notesService: ChatsServices): ChatsDataContract.Remote = ChatsRemoteData(notesService)

    @Provides
    @ChatsScope
    fun localData(notesDB: NotesDB): ChatsDataContract.Local = ChatsLocalData(notesDB)

    @Provides
    @ChatsScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ChatsScope
    fun postService(retrofit: Retrofit): ChatsServices = retrofit.create(ChatsServices::class.java)

    /*Parent providers to dependents*/
    @Provides
    @ChatsScope
    fun notesDb(context: Context): NotesDB = Room.databaseBuilder(context, NotesDB::class.java, Constants.Posts.DB_NAME).build()


}