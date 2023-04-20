package com.example.basicactivity.fragments.sitesPages;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;
import com.google.android.material.slider.Slider;

public class Gallery extends Fragment {

    TextView delayTextSlider;
    Slider delaySlider;
    TextView CQTextSlider;
    Slider CQSlider;
    TextView textLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Delay - slider
        delaySlider = view.findViewById(R.id.galleryDelaySlider);
        delayTextSlider = view.findViewById(R.id.textGalleryDelaySelection);
        setSliderDelay();

        // Compression Quality - slider
        CQSlider = view.findViewById(R.id.compressionQualitySlider);
        CQTextSlider = view.findViewById(R.id.textCompressionQualitySelection);
        setSliderCQ();

        textLog = view.findViewById(R.id.logGallery);

        return view;
    }

    public void setSliderDelay() {
        delayTextSlider.setTypeface(Typeface.DEFAULT_BOLD);
        delaySlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            public void onStopTrackingTouch(@NonNull Slider slider) {
                String str = Float.toString(slider.getValue());
                String res = (String) textLog.getText();
                textLog.setText(res + "\nSlider moved at " + MainActivity.printHeure() + "\n " + MyClient.setStr("GalleryDelay : " + str));
            }
        });
    }

    public void setSliderCQ() {
        CQTextSlider.setTypeface(Typeface.DEFAULT_BOLD);
        CQSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(@NonNull Slider slider) {

            }
            public void onStopTrackingTouch(@NonNull Slider slider) {
                String str = Float.toString(slider.getValue());
                String res = (String) textLog.getText();
                textLog.setText(res + "\nSlider moved at " + MainActivity.printHeure() + "\n " + MyClient.setStr("CompressionQuality : " + str));
            }
        });
    }

}
