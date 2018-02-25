package com.castrec.stephane.noteskotlinsample.chat.services

import android.arch.persistence.room.*
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 22/02/2018.
 */
@Dao
interface ChatsDao {

    @Query("SELECT * from Chats")
    fun getChats(): Flowable<List<Chat>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(notes: List<Chat>)
}