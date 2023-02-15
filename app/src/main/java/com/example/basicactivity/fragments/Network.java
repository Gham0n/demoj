package com.example.basicactivity.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.basicactivity.R;

public class Network extends Fragment {
    SwitchCompat sw;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);

        // Bouton switch pour la perte de paquet
        sw = (SwitchCompat) view.findViewById(R.id.SwitchPaquet);
        setSwitch();

        return view;
    }

    public void setSwitch() {
        sw.setTypeface(Typeface.DEFAULT_BOLD);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("The toggle is enabled");
                    // TODO add actions
                } else {
                    System.out.println("The toggle is disabled");
                    // TODO add actions
                }
            }
        });
    }
}