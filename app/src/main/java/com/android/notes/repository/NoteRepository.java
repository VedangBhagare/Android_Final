package com.android.notes.repository;

import androidx.annotation.NonNull;

import com.android.notes.data.model.Note;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

/**
 * This is the interface for the NoteRepository. It defines the methods
 * for accessing and manipulating the data related to notes in the app.
 */

public interface NoteRepository {

    @NonNull
    Flowable<List<Note>> getAll();

    @NonNull
    Flowable<Note> getById(long id);

    @NonNull
    Single<Long> insert(Note note);

    void update(Note note);

    void deleteById(long id);
}