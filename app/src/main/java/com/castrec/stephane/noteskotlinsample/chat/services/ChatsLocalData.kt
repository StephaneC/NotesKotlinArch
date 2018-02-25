package com.castrec.stephane.noteskotlinsample.chat.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class ChatsLocalData(private val notesDb: NotesDB): ChatsDataContract.Local {


    override fun fetchChats(): Flowable<List<Chat>> {
        Log.d("KotlinChats", "chats Local fetch")
        return notesDb.chatsDao().getChats()
    }

    override fun saveChats(chats: List<Chat>) {
        Log.d("KotlinNotes", "chats Local save")
        notesDb.chatsDao().upsertAll(chats)
    }


}