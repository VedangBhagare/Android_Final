package com.android.notes.di;

import android.content.Context;

import com.android.notes.ui.details.DetailsFragment;
import com.android.notes.ui.home.HomeFragment;
import com.android.notes.ui.search.SearchFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

/**
 * AppComponent is the main Dagger component responsible for providing dependencies
 * to the app's fragments, as well as some shared resources like CompositeDisposable.
 * Dagger is a dependency injection framework that helps manage and provide dependencies
 * for the app in a clean and efficient way.
 */

@Component(
        modules = {
                ViewModelModule.class,
                RepositoryModule.class,
                DisposableModule.class,
                DatabaseModule.class,
                PreferencesModule.class
        }
)
@Singleton
public interface AppComponent {

    void inject(HomeFragment fragment);

    void inject(DetailsFragment fragment);

    void inject(SearchFragment fragment);

    @ApplicationDisposable
    CompositeDisposable getApplicationDisposable();

    @Component.Factory
    interface Factory {

        AppComponent create(@BindsInstance Context context);
    }
}