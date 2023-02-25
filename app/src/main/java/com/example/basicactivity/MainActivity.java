package com.example.basicactivity;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.Formatter;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 vp;
    PagerAdapter pa;

    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.demoJLogo);

        tabLayout = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.view_pager);
        pa = new PagerAdapter(this);

        vp.setAdapter(pa);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                vp.setCurrentItem(position);
                setColors(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
    }

    public void setColors(int selectedTab) {
        int black = ContextCompat.getColor(this, R.color.black);
        int color = black;

        switch (selectedTab) {
            case 0:
                color = ContextCompat.getColor(this, R.color.red);
                break;
            case 1:
                color = ContextCompat.getColor(this, R.color.blue);
                break;
            case 2:
                color = ContextCompat.getColor(this, R.color.green);
                break;
        }

        mainView.setBackgroundColor(color);
        tabLayout.setSelectedTabIndicatorColor(color);
        tabLayout.setTabTextColors(black, color);
    }

    public static String printHeure() {
        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format = new Formatter();
        format.format("%tl:%tM", gfg_calender,
                gfg_calender);

        // Printing the current hour and minute
        return format.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void onClickVega(View view) throws IOException, InterruptedException {
        System.out.println("Vega button pushed at " + printHeure());

        TextView tmp = (TextView) findViewById(R.id.textVLog);
        String res = (String) tmp.getText();
        tmp.setText(res +"\nVega button pushed at "  + printHeure() + "\n\n" + ping.ping("10.3.141.1"));
    }

    public void onClickDeneb(View view) throws IOException, InterruptedException {
        System.out.println("Deneb button pushed at " + printHeure());
        TextView tmp = (TextView) findViewById(R.id.textVLog);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nDeneb button pushed at " + printHeure() + "\n\n" + ping.ping("10.3.141.203"));
    }

    public void onClickAltair(View view) throws IOException, InterruptedException {
        System.out.println("Altaïr button pushed at " + printHeure());
        TextView tmp = (TextView) findViewById(R.id.textVLog);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nVega button pushed at " + printHeure() + "\n\n" + ping.ping("10.3.141.226"));
    }

    public void onClickVegaConnect(View view) throws Exception {
        System.out.println("Connect button pushed at " + printHeure());

        MyClient.run();

    }

    public void onClickVegaSend(View view) throws IOException, InterruptedException {
        System.out.println("Send button pushed at " + printHeure());

    }
    public void onClickVegaStop(View view) throws IOException, InterruptedException {
        System.out.println("Stop button pushed at " + printHeure());

    }
}