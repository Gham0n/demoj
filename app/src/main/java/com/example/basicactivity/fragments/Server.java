package com.example.basicactivity.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basicactivity.R;
import com.example.basicactivity.fragments.sitesPages.*;

public class Server extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_server, container, false);
    }

    /**
     * The fragment showed will change according to the selected site in the Terminal.
     * @param site selected site
     */
    public void setSite(int site) {
        Fragment siteFragment = new NoWebsite();
        switch (site) {
            case 1:
                siteFragment = new Calculator();
                break;
            case 2:
                siteFragment = new Subway();
                break;
            case 3:
                siteFragment = new Gallery();
                break;
        }
        getChildFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, siteFragment).commit();
    }
}