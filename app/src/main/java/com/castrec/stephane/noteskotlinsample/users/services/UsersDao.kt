package com.castrec.stephane.noteskotlinsample.users.services

import android.arch.persistence.room.*
import com.castrec.stephane.noteskotlinsample.users.model.User
import io.reactivex.Flowable
import java.util.List
/**
 * Created by sca on 22/02/2018.
 */
@Dao
interface UsersDao {

    @Query("SELECT * from Users")
    fun getUsers(): Flowable<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(users: List<User>)
}