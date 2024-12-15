package com.android.notes.data.preferences;

import androidx.annotation.NonNull;

import io.reactivex.rxjava3.core.Flowable;

/**
 * This interface defines methods for managing user preferences in the app,
 * such as getting the current preferences and toggling between different rendering styles.
 */

public interface AppPreferencesManager {

    @NonNull
    Flowable<AppPreferences> getAll();

    void toggleRendering();
}