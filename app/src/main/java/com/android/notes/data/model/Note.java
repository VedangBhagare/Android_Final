package com.android.notes.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

/**
 * This class represents a "note" in the app. It is used to store data about each note
 * and also defines how the note is stored in the Room database.
 */
@Entity(tableName = "note")
public class Note {

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) // This is the primary key of the note (unique identifier).
    public long id;

    // The title of the note.
    @ColumnInfo(name = "title")
    public String title;

    // The content of the note (the actual text).
    @ColumnInfo(name = "content")
    public String content;

    // The date and time when the note was created or last updated.
    @ColumnInfo(name = "date_time")
    public LocalDateTime dateTime;

    // Whether the note is pinned or not (true if pinned, false if not).
    @ColumnInfo(name = "is_pinned")
    public boolean isPinned;

    // A constant representing an empty ID value (0 is considered as empty ID).
    public static long EMPTY_ID = 0;

    /**
     * Default constructor that creates a new note with default values:
     */
    public Note() {
        this(
                EMPTY_ID,
                "", "",
                LocalDateTime.now(ZoneId.systemDefault()),
                false
        );
    }

    public Note(
            long id,
            String title,
            String content,
            LocalDateTime dateTime,
            boolean isPinned
    ) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.isPinned = isPinned;
    }

    /**
     * Checks if two notes are equal by comparing their properties (id, title, content, etc.).
     * This is used for comparing notes in lists or other collections.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id &&
                isPinned == note.isPinned &&
                Objects.equals(title, note.title) &&
                Objects.equals(content, note.content) &&
                Objects.equals(dateTime, note.dateTime);
    }

    /**
     * Generates a unique hash code for the note based on its properties.
     * This is used for storing and searching the note in hash-based collections like HashMap.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, dateTime, isPinned);
    }
}
