package com.android.notes.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.android.notes.data.model.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * This interface, it is used to access and manipulate data for notes.
 */
@Dao // Marks this interface as a Data Access Object (DAO) for Room database operations
public interface NoteDao {

    /**
     * Fetches all the notes from the database.
     */
    @Query("SELECT * FROM note")
    Flowable<List<Note>> getAll();

    /**
     * Fetches a single note by its ID.
     */
    @Query("SELECT * FROM note WHERE id = :id")
    Flowable<Note> getById(long id);

    /**
     * Inserts a new note into the database.
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    Single<Long> insert(Note note);

    /**
     * Updates an existing note in the database.
     */
    @Update(onConflict = OnConflictStrategy.ABORT)
    Completable update(Note note);

    /**
     * Deletes a note from the database by its ID.
     */
    @Query("DELETE FROM note WHERE id = :id")
    Completable deleteById(long id);
}
