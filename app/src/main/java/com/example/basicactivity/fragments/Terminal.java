package com.example.basicactivity.fragments;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.R;
import com.google.android.material.slider.Slider;

import java.util.Calendar;
import java.util.Formatter;


public class Terminal extends Fragment {
    SwitchCompat sw;
    TextView textSlider;
    Slider slider;
    Spinner dropDownMenu;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_terminal, container, false);

        // Bouton switch pour l'écran d'affichage
        sw = (SwitchCompat) view.findViewById(R.id.SwitchEcran);
        setSwitch();

        // Slider pour le nombre de requêtes
        slider = (Slider) view.findViewById(R.id.SliderRequêtes);
        textSlider = (TextView) view.findViewById(R.id.TextRequêtes);
        setSlider();

        // Drop down menu pour la selection des sites
        dropDownMenu = view.findViewById(R.id.SelectSite);
        String[] items = new String[]{"1 - CALCULATRICE", "2 - DEMOSTAR", "3 - STREAMING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropDownMenu.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

    public void setSlider() {
        textSlider.setTypeface(Typeface.DEFAULT_BOLD);
        slider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            public void onStartTrackingTouch(Slider slider) {

            }
            public void onStopTrackingTouch(Slider slider) {
                System.out.println("The slider value is " + slider.getValue());
                // TODO add actions
            }
        });
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

    public void sendRequest(View view) {
        /**
        final TextView textView = (TextView) findViewById(R.id.text);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://176.128.224.183/";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        textView.setText("Response is: " + response.substring(0,500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
            }

        });


// Add the request to the RequestQueue.
        queue.add(stringRequest);**/
    }

}