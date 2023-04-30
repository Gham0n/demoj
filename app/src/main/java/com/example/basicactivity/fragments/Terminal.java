package com.example.basicactivity.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;
import com.google.android.material.slider.Slider;


public class Terminal extends Fragment {
    TextView textNav;
    TextView textSites;
    TextView textSlider;
    Spinner dropDownMenu;
    Slider slider;
    Button chromium;
    Button midori;
    TextView textLog;
    int site;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_terminal, container, false);

        // Navigator selection - buttons
        textNav = view.findViewById(R.id.textBrowserSelection);
        chromium = view.findViewById(R.id.chromiumButton);
        midori = view.findViewById(R.id.midoriButton);

        LinearLayout layout = view.findViewById(R.id.browserButtonsLayout);
        chromium.setWidth(layout.getWidth()/2);
        midori.setWidth(layout.getWidth()/2);
        setButtons();

        // Requests - slider
        textSlider = view.findViewById(R.id.textRequestSlider);
        slider = view.findViewById(R.id.requestSlider);
        setSlider();

        // Site selection - drop down menu
        textSites = view.findViewById(R.id.textWebsiteSelection);
        dropDownMenu = view.findViewById(R.id.websiteSpinner);
        String[] items = new String[]{"0 - NO WEBSITE" , "1 - CALCULATOR", "2 - SUBWAY", "3 - GALLERY"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropDownMenu.setAdapter(adapter);
        setSpinner();

        textLog = view.findViewById(R.id.logTerminal);

        return view;
    }

    public void setSlider() {
        textSlider.setTypeface(Typeface.DEFAULT_BOLD);
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            public void onStopTrackingTouch(@NonNull Slider slider) {
                String str = Float.toString(slider.getValue());
                String log = textLog.getText() + "\nSlider moved at " + MainActivity.printTime() + "\n " + MyClient.setStr("NbRequests : " + str);
                textLog.setText(log);
            }
        });
    }

    public void setButtons() {
        textNav.setTypeface(Typeface.DEFAULT_BOLD);
        midori.setOnClickListener(view -> {
            chromium.setEnabled(true);
            midori.setEnabled(false);

            String log = textLog.getText() + "\nMidori button pushed at " + MainActivity.printTime() + "\n " + MyClient.setStr("Browser : midori");
            textLog.setText(log);

            chromium.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));
            midori.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                chromium.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                midori.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.red)));
            }
        });

        chromium.setOnClickListener(view -> {
            chromium.setEnabled(false);
            midori.setEnabled(true);

            String log = textLog.getText() + "\nChromium button pushed at " + MainActivity.printTime() + "\n " + MyClient.setStr("Browser : chromium");
            textLog.setText(log);

            chromium.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
            midori.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                chromium.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.red)));
                midori.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
            }
        });
    }

    public void setSpinner() {
        textSites.setTypeface(Typeface.DEFAULT_BOLD);
        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                site = (dropDownMenu.getSelectedItemPosition());
                MainActivity.setSite(site); // sets fragment for Server

                String log = textLog.getText() + "\nSpinner is selected at " + MainActivity.printTime() + "\n " + MyClient.setStr("Website : " + site);
                textLog.setText(log);
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nothing is selected.");
            }
        });
    }
}