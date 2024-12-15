package com.android.notes.data.local;

import androidx.annotation.NonNull;
import androidx.room.TypeConverter;

import java.time.LocalDateTime;

/**
 * Room database does not support LocalDateTime directly, so we need these methods.
 */
public class AppDatabaseConverters {

    /**
     * Converts a LocalDateTime object to a String.
     */
    @NonNull
    @TypeConverter
    public static String fromLocalDateTime(@NonNull LocalDateTime dateTime) {
        return dateTime.toString();
    }

    /**
     * Converts a string back to a LocalDateTime object.
     */
    @NonNull
    @TypeConverter
    public static LocalDateTime toLocalDateTime(@NonNull String isoString) {
        return LocalDateTime.parse(isoString);
    }
}
