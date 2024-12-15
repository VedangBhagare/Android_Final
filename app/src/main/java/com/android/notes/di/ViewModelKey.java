package com.android.notes.di;

import androidx.lifecycle.ViewModel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import dagger.MapKey;

/**
 * Custom annotation used as a key in Dagger's multibinding for ViewModels.
 * This annotation helps Dagger identify which ViewModel class to provide when needed.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
public @interface ViewModelKey {

    Class<? extends ViewModel> value();
}