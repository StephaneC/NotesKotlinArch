package com.castrec.stephane.noteskotlinsample.chat.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import com.castrec.stephane.noteskotlinsample.chat.services.ChatsDataContract
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.BackpressureStrategy

import java.util.List

/**
 * Created by sca on 19/02/2018.
 *
 * In charge of managing data.
 * - Fetch from api and stor into DB
 */
class ChatsRepository(
        private val local: ChatsDataContract.Local,
        private val remote: ChatsDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
): ChatsDataContract.Repository {

    override fun fetchChats(): Flowable<List<Chat>> {
        return Flowable.create({ emitter: FlowableEmitter<List<Chat>> ->
            //first send stuff from local
            Log.d("KotlinChats", "Loading notes")
            local.fetchChats().subscribe({
                chats ->
                Log.d("KotlinChats", "Loaded notes from local")
                emitter.onNext(chats)
                //then fecth from remote
                remote.fetchChats().subscribe(
                        {chats ->
                            Log.d("KotlinChats", "Loaded notes from remote")
                            emitter.onNext(chats)
                            local.saveChats(chats)
                            emitter.onComplete()
                        },
                        {error -> emitter.onError(error)})
            })
        }, BackpressureStrategy.BUFFER)
    }

}