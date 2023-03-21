package com.example.basicactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.basicactivity.fragments.*;

public class PagerAdapter extends FragmentStateAdapter {
    int site;
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int index) {
        switch (index) {
            case 1:
                return new Network();
            case 2:
                //switch (site) {
                //    case 1:
                        return new Calculator();
                //    default:
                //        return new Server();
                //}
            default:
                return new Terminal();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // number of fragments (tabs)
    }

    public void setSite(int selectedSite) {
        site = selectedSite;
    }
}
