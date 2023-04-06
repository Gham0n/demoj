package com.example.basicactivity.fragments.sitesPages;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.basicactivity.MainActivity;
import com.example.basicactivity.MyClient;
import com.example.basicactivity.R;

public class Streaming extends Fragment {

    TextView textQuality;
    Spinner dropDownMenu;
    TextView textLog;
    int quality;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_streaming, container, false);

        // Quality selection - drop down menu
        textQuality = view.findViewById(R.id.TextQuality);
        dropDownMenu = view.findViewById(R.id.SelectQuality);
        String[] items = new String[]{"High Definition (4K)", "Regular (1080p)", "Low Definition (480p)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        dropDownMenu.setAdapter(adapter);
        setSpinner();

        textLog = view.findViewById(R.id.textVLog);

        return view;
    }

    public void setSpinner(){
        textQuality.setTypeface(Typeface.DEFAULT_BOLD);
        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String res = (String) textLog.getText();
                quality = (dropDownMenu.getSelectedItemPosition()+1);

                textLog.setText(res + "\nSpinner is selected at " + MainActivity.printHeure() + "\n " + MyClient.setStr("Quality " + quality));
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("Nothing is selected.");
            }
        });
    }

}
