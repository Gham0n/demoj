package com.example.basicactivity.fragments.sitesPages;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;
import com.google.android.material.slider.Slider;

public class Calculator extends Fragment {
    TextView textSlider;
    TextView textSource;
    Slider slider;
    Button client;
    Button server;
    TextView textLog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        // Slider pour le nombre de calculs
        slider = (Slider) view.findViewById(R.id.SliderCalculs);
        textSlider = (TextView) view.findViewById(R.id.TextCalculs);
        setSlider();

        // Buttons - navigator selection
        textSource = (TextView) view.findViewById(R.id.textnav);
        client = (Button) view.findViewById(R.id.calcClient);
        server = (Button) view.findViewById(R.id.calcServeur);
        setButtons();

        textLog = (TextView) view.findViewById(R.id.textVLog);

        return view;
    }

    public void setSlider() {
        textSlider.setTypeface(Typeface.DEFAULT_BOLD);
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(Slider slider) {

            }
            public void onStopTrackingTouch(Slider slider) {
                System.out.println("The slider value is " + slider.getValue());
                String str = Float.toString(slider.getValue());
                String res = (String) textLog.getText();
                textLog.setText(res + "\nSlider moved at " + MainActivity.printHeure() + "\n " + MyClient.setStr("request " + str));
            }
        });
    }

    public void setButtons() {
        textSource.setTypeface(Typeface.DEFAULT_BOLD);
        server.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Server pushed");
                client.setEnabled(true);
                server.setEnabled(false);

                String res = (String) textLog.getText();
                textLog.setText(res + "\nServer button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("nav midori"));

                client.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));
                server.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    client.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                    server.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.green)));
                }
            }
        });

        client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Client pushed");

                client.setEnabled(false);
                server.setEnabled(true);

                String res = (String) textLog.getText();
                textLog.setText(res + "\nClient button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("nav firefox"));

                client.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
                server.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    client.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.green)));
                    server.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                }
            }
        });
    }

}