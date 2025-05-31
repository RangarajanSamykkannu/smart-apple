package com.example.tabviewandroid;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TabsPagerAdapter tabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        // Initialize adapter
        tabsPagerAdapter = new TabsPagerAdapter(this);
        viewPager.setAdapter(tabsPagerAdapter);

        // Set offScreenPageLimit to keep all tabs in memory (5 tabs total - 1 current = 4 offscreen)
        // This is crucial for preventing fragments from being destroyed and recreated.
        viewPager.setOffscreenPageLimit(4);

        // Link TabLayout with ViewPager2 and set tab titles
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Tab 1");
                            break;
                        case 1:
                            tab.setText("Tab 2");
                            break;
                        case 2:
                            tab.setText("Tab 3");
                            break;
                        case 3:
                            tab.setText("Tab 4");
                            break;
                        case 4:
                            tab.setText("Tab 5");
                            break;
                    }
                }).attach();
    }
}
