package com.android.notes.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Custom annotation to mark a dependency that should be used for managing RxJava subscriptions.
 * This annotation is used to distinguish a specific instance of CompositeDisposable.
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationDisposable {
}