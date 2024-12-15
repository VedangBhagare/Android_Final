package com.android.notes.di;

import android.content.Context;

import androidx.room.RoomDatabase;

import com.android.notes.data.local.AppDatabase;
import com.android.notes.data.local.NoteDao;
import com.android.notes.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This module provides the database and DAO (Data Access Object) dependencies
 * using Dagger's dependency injection system.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Context context) {
        return new RoomDatabase
                .Builder<>(
                    context,
                    AppDatabase.class,
                    Constants.ROOM_DATABASE_NAME
                )
                .build();
    }

    @Provides
    public NoteDao provideNoteDao(AppDatabase appDatabase) {
        return appDatabase.getNoteDao();
    }
}