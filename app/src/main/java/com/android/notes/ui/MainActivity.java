package com.android.notes.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.notes.Notes;
import com.android.notes.databinding.ActivityMainBinding;

public final class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isFinishing()) {
            ((Notes) getApplicationContext())
                    .appComponent
                    .getApplicationDisposable()
                    .dispose();
        }
    }
}