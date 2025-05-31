package com.example.tabviewandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DetailsLevel1Fragment extends Fragment {

    public DetailsLevel1Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_details_level1, container, false);

        Button buttonToDetailsL2 = view.findViewById(R.id.button_to_details_level2);
        buttonToDetailsL2.setOnClickListener(v -> {
            // Get parent fragment that implements NavigationHostFragment
            Fragment parentHost = getParentFragment();
            if (parentHost instanceof NavigationHostFragment) {
                ((NavigationHostFragment) parentHost).navigateToDetailsLevel2();
            }
        });
        return view;
    }
}
