package com.manunimahmud.instafollow.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manunimahmud.instafollow.R;
import com.manunimahmud.instafollow.adapters.ViewPagerAdapter;
import com.manunimahmud.instafollow.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {
FragmentNotificationBinding binding;


    public NotificationFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        binding.viewPager.setAdapter(new ViewPagerAdapter(getFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

        return binding.getRoot();
    }
}