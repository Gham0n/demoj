package com.example.basicactivity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basicactivity.PagerAdapter;
import com.example.basicactivity.R;
import com.example.basicactivity.fragments.sitesPages.Calculator;
import com.example.basicactivity.fragments.sitesPages.Demostar;
import com.google.android.material.tabs.TabLayout;

public class Server extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fragment demostar = new Demostar();
        //getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, demostar).commit();
        //Fragment calculator = new Calculator();
        //getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, calculator).commit();

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_server, container, false);
    }

    public void setSite(int site) {
        Fragment siteFragment;
        switch (site) {
            case 1:
                siteFragment = new Calculator();
                getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, siteFragment).commit();
                break;
            case 2:
                siteFragment = new Demostar();
                getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, siteFragment).commit();
                break;
            case 3:
                siteFragment = new Calculator();
                getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, siteFragment).commit();
                break;
        }
    }
}