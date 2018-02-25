package com.castrec.stephane.noteskotlinsample.users.services

import android.util.Log
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.users.model.User
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
class UsersRepository(
        private val local: UsersDataContract.Local,
        private val remote: UsersDataContract.Remote,
        private val scheduler: Scheduler,
        private val compositeDisposable: CompositeDisposable
): UsersDataContract.Repository {

    override fun fetchUsers(): Flowable<List<User>> {
        return Flowable.create({ emitter: FlowableEmitter<List<User>> ->
            //first send stuff from local
            Log.d("KotlinNotes", "Loading users")
            local.fetchUsers().subscribe({
                users ->
                Log.d("KotlinNotes", "Loaded users from local")
                emitter.onNext(users)
                //then fecth from remote
                remote.fetchUsers().subscribe(
                        {users ->
                            Log.d("KotlinNotes", "Loaded users from remote")
                            emitter.onNext(users)
                            local.saveUsers(users)
                            emitter.onComplete()
                        },
                        {error -> emitter.onError(error)}
                )
            })
        }, BackpressureStrategy.BUFFER)
    }

}