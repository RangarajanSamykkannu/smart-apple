package com.example.tabviewandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

// Tab1Fragment should implement NavigationHostFragment
public class Tab1Fragment extends Fragment implements NavigationHostFragment {

    public Tab1Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflates the layout which is now a FragmentContainerView
        return inflater.inflate(R.layout.fragment_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            // Load Tab1InitialContentFragment by default
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.tab_content_container, new Tab1InitialContentFragment())
                    .commit();
        }
    }

    @Override
    public void navigateToDetailsLevel1() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tab_content_container, new DetailsLevel1Fragment())
                .addToBackStack(null) // Allows returning to Tab1InitialContentFragment
                .commit();
    }

    @Override
    public void navigateToDetailsLevel2() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tab_content_container, new DetailsLevel2Fragment())
                .addToBackStack(null) // Allows returning to DetailsLevel1Fragment
                .commit();
    }
}
