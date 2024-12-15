package com.android.notes.ui.search;

import static androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS;
import static com.android.notes.data.preferences.AppPreferences.Rendering;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.android.notes.Notes;
import com.android.notes.R;
import com.android.notes.data.preferences.AppPreferences;
import com.android.notes.databinding.FragmentSearchBinding;
import com.android.notes.ui.MultiViewModelFactory;
import com.android.notes.ui.details.DetailsFragment;
import com.android.notes.utils.ContextUtils;
import com.android.notes.utils.DefaultTextWatcher;

import javax.inject.Inject;

public class SearchFragment extends Fragment {

    @Inject
    public MultiViewModelFactory viewModelFactory;

    private FragmentSearchBinding binding;

    private SearchViewModel viewModel;

    private SearchAdapter searchAdapter;

    private boolean isOpenedFirstTime;

    private final TextWatcher searchTextWatcher = new SearchTextWatcher();

    public SearchFragment() {
        super(R.layout.fragment_search);
    }

    private void setupUi() {
        binding.toolbar.setNavigationOnClickListener((view) -> {
            ContextUtils.hideSoftInput(requireContext(), binding.getRoot());
            getParentFragmentManager().popBackStack();
        });
        binding.etFilter.addTextChangedListener(searchTextWatcher);
        if (isOpenedFirstTime) {
            ContextUtils.showSoftInput(requireContext(), binding.etFilter);
        }
        binding.ivClear.setOnClickListener((view) -> {
            binding.etFilter.getText().clear();
            viewModel.clear();
        });

        binding.rvNotes.setHasFixedSize(true);
        binding.rvNotes.setAdapter(searchAdapter);
    }

    private void setupObservers() {
        viewModel.notes.observe(
                getViewLifecycleOwner(),
                searchAdapter::submitList
        );
        viewModel.pattern.observe(
                getViewLifecycleOwner(),
                searchAdapter::submitPattern
        );
        viewModel.rendering.observe(
                getViewLifecycleOwner(),
                this::showRendering
        );
    }

    private void showRendering(AppPreferences.Rendering rendering) {
        RecyclerView.LayoutManager manager;
        if (rendering == Rendering.LIST) {
            manager = new LinearLayoutManager(requireContext());
        } else {
            StaggeredGridLayoutManager staggered = new StaggeredGridLayoutManager(
                    2, StaggeredGridLayoutManager.VERTICAL
            );
            staggered.setGapStrategy(GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
            manager = staggered;
        }
        binding.rvNotes.setLayoutManager(manager);
        searchAdapter.setLayoutManager(manager);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        ((Notes) context.getApplicationContext()).appComponent.inject(this);
        searchAdapter = new SearchAdapter(
                requireContext(),
                (note) -> {
                    Bundle args = new Bundle();
                    args.putLong(DetailsFragment.Args.NOTE_ID, note.id);
                    ContextUtils.hideSoftInput(requireContext(), binding.getRoot());
                    getParentFragmentManager().beginTransaction()
                            .setReorderingAllowed(true)
                            .add(R.id.fcv_main, DetailsFragment.class, args)
                            .addToBackStack(null)
                            .commit();
                }
        );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isOpenedFirstTime = savedInstanceState == null;
        binding = FragmentSearchBinding.bind(view);
        viewModel = new ViewModelProvider(
                getViewModelStore(), viewModelFactory
        ).get(SearchViewModel.class);
        setupUi();
        setupObservers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private class SearchTextWatcher implements DefaultTextWatcher {

        @Override
        public void afterTextChanged(Editable s) {
            String filter = binding.etFilter.getText().toString().trim();
            binding.ivClear.setVisibility(!filter.isEmpty() ? View.VISIBLE : View.GONE);
            viewModel.search(filter);
        }
    }
}