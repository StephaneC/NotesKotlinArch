package com.castrec.stephane.noteskotlinsample.di

import com.castrec.stephane.noteskotlinsample.CoreApp
import com.castrec.stephane.noteskotlinsample.notes.di.NotesComponent
import com.castrec.stephane.noteskotlinsample.users.di.AuthenticationComponent
import com.castrec.stephane.noteskotlinsample.users.di.DaggerAuthenticationComponent
import com.castrec.stephane.noteskotlinsample.users.di.DaggerUsersComponent
import com.castrec.stephane.noteskotlinsample.notes.di.DaggerNotesComponent
import com.castrec.stephane.noteskotlinsample.users.di.UsersComponent
import javax.inject.Singleton

/**
 * Created by sca on 20/02/2018.
 */
@Singleton
object NotesDH {
    private var authenticationComponent: AuthenticationComponent? = null
    private var usersComponent: UsersComponent? = null
    private var notesComponent: NotesComponent? = null

    fun authenticationComponent(): AuthenticationComponent {
        if (authenticationComponent == null)
            authenticationComponent = DaggerAuthenticationComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return authenticationComponent as AuthenticationComponent
    }

    fun usersComponent(): UsersComponent {
        if (usersComponent == null)
            usersComponent = DaggerUsersComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return usersComponent as UsersComponent
    }

    fun notesComponent(): NotesComponent {
        if (notesComponent == null)
            notesComponent = DaggerNotesComponent.builder().coreComponent(CoreApp.coreComponent).build()
        return notesComponent as NotesComponent
    }

    fun destroyListComponent() {
        authenticationComponent = null
        usersComponent = null
    }
}