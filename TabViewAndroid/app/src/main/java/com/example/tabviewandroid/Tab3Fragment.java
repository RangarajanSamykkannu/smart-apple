package com.example.tabviewandroid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Tab3Fragment extends Fragment implements NavigationHostFragment {

    public Tab3Fragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.tab_content_container, new Tab3InitialContentFragment())
                    .commit();
        }
    }

    @Override
    public void navigateToDetailsLevel1() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tab_content_container, new DetailsLevel1Fragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void navigateToDetailsLevel2() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tab_content_container, new DetailsLevel2Fragment())
                .addToBackStack(null)
                .commit();
    }
}
