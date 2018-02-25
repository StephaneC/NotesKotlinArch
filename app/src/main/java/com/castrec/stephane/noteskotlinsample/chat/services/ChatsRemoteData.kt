package com.castrec.stephane.noteskotlinsample.chat.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import java.util.List

/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class ChatsRemoteData(private val chatsService: ChatsServices): ChatsDataContract.Remote {
    override fun fetchChats(): Flowable<List<Chat>> {
        Log.d("KotlinChats", "Users remote fetch")
        return chatsService.fetchChats();
    }
}