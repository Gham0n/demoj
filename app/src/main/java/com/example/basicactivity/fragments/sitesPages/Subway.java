package com.example.basicactivity.fragments.sitesPages;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;

public class Subway extends Fragment {

    SwitchCompat switchRefresh;
    TextView textLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subway, container, false);

        // Screen - switch
        switchRefresh = view.findViewById(R.id.refreshSelection);
        setSwitch();

        textLog = view.findViewById(R.id.logGallery);

        return view;
    }

    public void setSwitch() {
        switchRefresh.setTypeface(Typeface.DEFAULT_BOLD);
        switchRefresh.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String log = (String) textLog.getText();
            if (isChecked) {
                log += "\nToggle is enabled at " + MainActivity.printTime() + "\n " + MyClient.setStr("AutoRefresh : true");
                textLog.setText(log);
            } else {
                log += "\nToggle is disabled at " + MainActivity.printTime() + "\n " + MyClient.setStr("AutoRefresh : false");
                textLog.setText(log);
            }
        });
    }
}
