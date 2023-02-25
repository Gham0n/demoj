package com.example.basicactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.basicactivity.fragments.*;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new Connect();
            case 2:
                return new Server();
            default:
                return new Terminal();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // number of fragments (tabs)
    }
}
