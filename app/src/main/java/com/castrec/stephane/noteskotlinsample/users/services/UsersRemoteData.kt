package com.castrec.stephane.noteskotlinsample.users.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import java.util.List

/**
 * Created by sca on 18/02/2018.
 * Class to get remote data.
 */
class UsersRemoteData(private val usersService: UsersServices): UsersDataContract.Remote {
    override fun fetchUsers(): Flowable<List<User>> {
        Log.d("KotlinNotes", "Users remote fetch")
        return usersService.fetchUsers();
    }
}