package com.android.notes.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * This module provides an instance of CompositeDisposable,
 * which helps in managing RxJava subscriptions in the app.
 */

@Module
public class DisposableModule {

    @Provides
    @ApplicationDisposable
    @Singleton
    public CompositeDisposable provideApplicationDisposable() {
        return new CompositeDisposable();
    }
}