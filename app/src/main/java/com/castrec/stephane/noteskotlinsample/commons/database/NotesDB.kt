package com.castrec.stephane.noteskotlinsample.commons.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.castrec.stephane.noteskotlinsample.chat.model.Chat
import com.castrec.stephane.noteskotlinsample.chat.services.ChatsDao
import com.castrec.stephane.noteskotlinsample.notes.model.Note
import com.castrec.stephane.noteskotlinsample.notes.services.NotesDao
import com.castrec.stephane.noteskotlinsample.users.model.User
import com.castrec.stephane.noteskotlinsample.users.services.UsersDao

/**
 * Created by sca on 22/02/2018.
 */
@Database(entities = [User::class, Note::class, Chat::class], version = 1,exportSchema = false)
abstract class NotesDB : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun notesDao(): NotesDao
    abstract fun chatsDao(): ChatsDao

}
