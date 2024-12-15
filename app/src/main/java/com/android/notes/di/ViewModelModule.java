package com.android.notes.di;

import androidx.lifecycle.ViewModel;

import com.android.notes.ui.home.HomeViewModel;
import com.android.notes.ui.search.SearchViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * This module binds specific ViewModel classes to their corresponding instances.
 * Dagger uses these bindings to provide the correct ViewModel when needed.
 */

@Module
public interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel.class)
    ViewModel bindSearchViewModel(SearchViewModel viewModel);
}