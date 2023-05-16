package com.manunimahmud.instafollow.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.manunimahmud.instafollow.fragments.Notification2Fragment;
import com.manunimahmud.instafollow.fragments.RequestFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new Notification2Fragment();
            case 1: return new RequestFragment();
            default: return new Notification2Fragment();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0){
            title = "NOTIFICATION";
        }else if (position == 1){
            title = "REQUEST";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
