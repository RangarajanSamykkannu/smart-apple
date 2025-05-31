package com.example.tabviewandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab4InitialContentFragment extends Fragment {

    public Tab4InitialContentFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tab4_initial_content, container, false);

        Button buttonToDetailsL1 = view.findViewById(R.id.button_to_details_level1);
        buttonToDetailsL1.setOnClickListener(v -> {
            // Get parent fragment that implements NavigationHostFragment
            Fragment parentHost = getParentFragment();
            if (parentHost instanceof NavigationHostFragment) {
                ((NavigationHostFragment) parentHost).navigateToDetailsLevel1();
            }
        });
        return view;
    }
}
