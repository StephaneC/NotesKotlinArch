package com.castrec.stephane.noteskotlinsample.users.di

import android.content.SharedPreferences
import com.castrec.stephane.noteskotlinsample.commons.Scheduler
import com.castrec.stephane.noteskotlinsample.di.CoreComponent
import com.castrec.stephane.noteskotlinsample.authentication.fragments.SigninFragment
import com.castrec.stephane.noteskotlinsample.commons.Session
import com.castrec.stephane.noteskotlinsample.users.services.*
import com.castrec.stephane.noteskotlinsample.users.viewmodel.AuthenticationViewModelFactory
import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

/**
 * Created by sca on 20/02/2018.
 */
@Component(dependencies = [CoreComponent::class], modules = [AuthenticationModule::class])
@AuthenticationScope
interface AuthenticationComponent {

    fun authenticationServices(): AuthenticationServices
    fun scheduler(): Scheduler

    fun inject(fragment: SigninFragment)
}

@AuthenticationScope
@Module
class AuthenticationModule {
    @Provides
    @AuthenticationScope
    fun authenticationViewModelFactory(repository: AuthenticationDataContract.Repository, compositeDisposable: CompositeDisposable): AuthenticationViewModelFactory = AuthenticationViewModelFactory(repository, compositeDisposable)

    /*Repository*/
    @Provides
    @AuthenticationScope
    fun usersRepo(session: Session, remote: AuthenticationDataContract.Remote, scheduler: Scheduler, compositeDisposable: CompositeDisposable): AuthenticationDataContract.Repository = AuthenticationRepository(session, remote, scheduler, compositeDisposable)

    @Provides
    @AuthenticationScope
    fun remoteData(usersService: AuthenticationServices): AuthenticationDataContract.Remote = AuthenticationRemoteData(usersService)

    @Provides
    @AuthenticationScope
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @AuthenticationScope
    fun postService(retrofit: Retrofit): AuthenticationServices = retrofit.create(AuthenticationServices::class.java)

}