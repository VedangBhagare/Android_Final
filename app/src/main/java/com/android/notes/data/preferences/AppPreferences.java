package com.android.notes.data.preferences;

import androidx.annotation.NonNull;

/**
 * This class stores user preferences related to the app's rendering style.
 * For example, it allows you to set whether the notes should be displayed in a list or grid format.
 */

public class AppPreferences {

    // The current rendering style (LIST or GRID).
    public final Rendering rendering;

    public AppPreferences(@NonNull Rendering rendering) {
        this.rendering = rendering;
    }

    public enum Rendering {
        LIST, GRID; // Represents the list and grid view style.

        Rendering opposite() {
            return this == LIST ? GRID : LIST;
        } // Switch between LIST and GRID.
    }
}