package com.android.notes.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.notes.utils.Constants;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * This module provides the SharedPreferences instance,
 * which allows reading and writing of app preferences/settings.
 */
@Module
public class PreferencesModule {

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return context.getSharedPreferences(
                Constants.PREFERENCES_FILE_NAME,
                Context.MODE_PRIVATE
        );
    }
}