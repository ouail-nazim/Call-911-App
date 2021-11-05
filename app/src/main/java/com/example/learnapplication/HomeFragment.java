package com.example.learnapplication;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnapplication.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    TabLayout tabLayout;
    ViewPager2 pager2;
    FragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = binding.tabLayout;
        pager2 = binding.viewPager2;
        FragmentManager fm = getActivity().getSupportFragmentManager();
        adapter = new FragmentAdapter(fm, getLifecycle());
        pager2.setAdapter(adapter);
        // Add tabs
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_fire_list));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_alert_fire));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_fire_my_account));
        // on tab click
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });



    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}