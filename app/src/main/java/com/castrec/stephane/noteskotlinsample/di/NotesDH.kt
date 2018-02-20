package com.castrec.stephane.noteskotlinsample.di

import com.castrec.stephane.noteskotlinsample.CoreApp
import com.castrec.stephane.noteskotlinsample.users.di.AuthenticationComponent
import com.castrec.stephane.noteskotlinsample.users.di.DaggerAuthenticationComponent
import javax.inject.Singleton

/**
 * Created by sca on 20/02/2018.
 */
@Singleton
object NotesDH {
    private var authenticationComponent: AuthenticationComponent? = null

    fun authenticationComponent(): AuthenticationComponent {
        if (authenticationComponent == null)
            authenticationComponent = DaggerAuthenticationComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return authenticationComponent as AuthenticationComponent
    }

    fun destroyListComponent() {
        authenticationComponent = null
    }
}