package com.example.basicactivity.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;
import com.google.android.material.slider.Slider;

public class Network extends Fragment {

    SwitchCompat sw;
    TextView textLog;
    TextView textSlider;
    Slider slider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_network, container, false);

        // Latency - slider
        slider = view.findViewById(R.id.galleryDelaySlider);
        textSlider = view.findViewById(R.id.textGalleryDelaySelection);
        setSlider();

        // Packet loss - switch
        sw = view.findViewById(R.id.SwitchPaquet);
        setSwitch();

        textLog = view.findViewById(R.id.textVLog2);

        return view;
    }

    public void setSlider() {
        textSlider.setTypeface(Typeface.DEFAULT_BOLD);
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            public void onStopTrackingTouch(@NonNull Slider slider) {
                String str = Float.toString(slider.getValue());
                String res = (String) textLog.getText();
                textLog.setText(res + "\nSlider moved at " + MainActivity.printHeure() + "\n " + MyClient.setStr("Latency : " + str));
            }
        });
    }

    public void setSwitch() {
        sw.setTypeface(Typeface.DEFAULT_BOLD);
        sw.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                String res = (String) textLog.getText();
                textLog.setText(res + "\nToggle is enabled at " + MainActivity.printHeure() + "\n " + MyClient.setStr("PacketLoss : true"));
            } else {
                String res = (String) textLog.getText();
                textLog.setText(res + "\nToggle is enabled at " + MainActivity.printHeure() + "\n " + MyClient.setStr("PacketLoss : false"));
            }
        });
    }
}