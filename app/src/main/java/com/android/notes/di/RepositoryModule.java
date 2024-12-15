package com.android.notes.di;

import com.android.notes.data.preferences.AppPreferencesManager;
import com.android.notes.data.preferences.AppPreferencesManagerImpl;
import com.android.notes.repository.NoteRepository;
import com.android.notes.repository.impl.NoteRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * This module is responsible for binding repository and manager interfaces to their implementations.
 * It allows Dagger to know which class to use when an instance of these interfaces is needed.
 */

@Module
public interface RepositoryModule {

    @Binds
    @Singleton
    NoteRepository bindNoteRepository(NoteRepositoryImpl impl);

    @Binds
    @Singleton
    AppPreferencesManager bindAppPreferencesManager(AppPreferencesManagerImpl impl);
}