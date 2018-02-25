package com.castrec.stephane.noteskotlinsample.users.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.database.NotesDB
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class UsersLocalData(private val notesDb: NotesDB): UsersDataContract.Local {


    override fun fetchUsers(): Flowable<List<User>> {
        Log.d("KotlinNotes", "Users Local fetch")
        return notesDb.usersDao().getUsers()
    }

    override fun saveUsers(users: List<User>) {
        Log.d("KotlinNotes", "Users Local save")
        notesDb.usersDao().upsertAll(users)
    }


}