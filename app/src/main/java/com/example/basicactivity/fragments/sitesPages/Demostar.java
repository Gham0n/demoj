package com.example.basicactivity.fragments.sitesPages;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;

public class Demostar extends Fragment {

    TextView textRefresh;
    Button auto;
    Button manual;
    TextView textLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_demostar, container, false);

        // Side selection - buttons
        textRefresh = view.findViewById(R.id.textRefresh);
        auto = view.findViewById(R.id.buttonAuto);
        manual = view.findViewById(R.id.buttonManual);
        setButtons();

        textLog = view.findViewById(R.id.textVLog);

        return view;
    }

    public void setButtons() {
        textRefresh.setTypeface(Typeface.DEFAULT_BOLD);
        auto.setOnClickListener(view -> {
            System.out.println("Server pushed");
            manual.setEnabled(true);
            auto.setEnabled(false);

            String res = (String) textLog.getText();
            textLog.setText(res + "\nServer button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("Refresh auto"));

            manual.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));
            auto.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                manual.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                auto.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.green)));
            }
        });

        manual.setOnClickListener(view -> {
            System.out.println("Client pushed");

            manual.setEnabled(false);
            auto.setEnabled(true);

            String res = (String) textLog.getText();
            textLog.setText(res + "\nClient button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("Refresh manual"));

            manual.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
            auto.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                manual.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.green)));
                auto.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
            }
        });
    }

}
