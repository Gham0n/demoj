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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;
import com.example.basicactivity.fragments.sitesPages.Calculator;
import com.example.basicactivity.fragments.sitesPages.Demostar;
import com.google.android.material.slider.Slider;

import java.util.Calendar;
import java.util.Formatter;


public class Terminal extends Fragment {
    SwitchCompat sw;
    TextView textSlider;
    TextView textNav;
    TextView textSites;
    Slider slider;
    Spinner dropDownMenu;
    Button firefox;
    Button midori;
    TextView textLog;
    int site;

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
        textSites = (TextView) view.findViewById(R.id.TextSites);
        dropDownMenu = view.findViewById(R.id.SelectSite);
        String[] items = new String[]{"1 - CALCULATRICE", "2 - DEMOSTAR", "3 - STREAMING"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropDownMenu.setAdapter(adapter);
        setSpinner();

        // Buttons - navigator selection
        textNav = (TextView) view.findViewById(R.id.textnav);
        firefox = (Button) view.findViewById(R.id.navFirefox);
        midori = (Button) view.findViewById(R.id.navMidori);
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

    public void setSwitch() {
        sw.setTypeface(Typeface.DEFAULT_BOLD);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println("The toggle is enabled");
                    String res = (String) textLog.getText();
                    textLog.setText(res + "\nToggle is enabled at " + MainActivity.printHeure() + "\n " + MyClient.setStr("screen on"));
                } else {
                    System.out.println("The toggle is disabled");
                    String res = (String) textLog.getText();
                    textLog.setText(res + "\nToggle is disabled at " + MainActivity.printHeure() + "\n " + MyClient.setStr("screen off"));
                }
            }
        });
    }

    public void setButtons() {
        textNav.setTypeface(Typeface.DEFAULT_BOLD);
        midori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Midori pushed");
                firefox.setEnabled(true);
                midori.setEnabled(false);

                String res = (String) textLog.getText();
                textLog.setText(res + "\nMidori button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("nav midori"));

                firefox.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));
                midori.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    firefox.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                    midori.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.red)));
                }
            }
        });

        firefox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Firefox pushed");

                firefox.setEnabled(false);
                midori.setEnabled(true);

                String res = (String) textLog.getText();
                textLog.setText(res + "\nMidori button pushed at " + MainActivity.printHeure() + "\n " + MyClient.setStr("nav firefox"));

                firefox.setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
                midori.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    firefox.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.red)));
                    midori.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(view.getContext(), R.color.white)));
                }
            }
        });
    }

    public void setSpinner(){
        textSites.setTypeface(Typeface.DEFAULT_BOLD);
        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String res = (String) textLog.getText();
                site = (dropDownMenu.getSelectedItemPosition()+1);

                MainActivity.setSite(site);

                textLog.setText(res + "\nSpinner is selected at " + MainActivity.printHeure() + "\n " + MyClient.setStr(String.valueOf("website " + site)));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nothing is selected");
            }
        });
    }
}