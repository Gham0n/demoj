package com.example.basicactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.basicactivity.fragments.Server;
import com.google.android.material.tabs.TabLayout;

import java.util.Formatter;
import java.util.Calendar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 vp;
    PagerAdapter pa;
    static FragmentManager fm;
    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainView = findViewById(R.id.demoJLogo);
        tabLayout = findViewById(R.id.tab_layout);
        vp = findViewById(R.id.view_pager);
        pa = new PagerAdapter(this);
        fm = this.getSupportFragmentManager();
        vp.setAdapter(pa);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                vp.setCurrentItem(position);

                setColors(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();
            }
        });

        popUpMenu();
    }

    /**
     * Sets up Popup Menu represented by a FloatingActionButton.
     * Two options : Connect and Stop.
     */
    public void popUpMenu() {
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.menuBurger));
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        findViewById(R.id.menuBurger).setOnClickListener(view -> popup.show());

        popup.setOnMenuItemClickListener(menuItem -> {
            if (menuItem.getItemId() == R.id.btn_conncect) { // Button 1
                System.out.println("Connect button pushed at " + printTime());

                // Launching client to connect to the server
                MyClient.launchClient();

                try { // Waiting for the connexion to be established
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Checking if connexion is established and showing the corresponding Toast message
                if(MyClient.getIsConnected())Toast.makeText(MainActivity.this, "Connected.", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, "Error. The server wasn't launched.", Toast.LENGTH_SHORT).show();
                return true;
            }
            else  if (menuItem.getItemId() == R.id.btn_quit) {
                System.out.println("Stop button pushed at " + printTime());

                // Sends signal to stop the server connexion
                MyClient.setStr("stop");

                // Toast message to indicate disconnection
                Toast.makeText(MainActivity.this, "Disconnection...", Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    /**
     * Set different themes and colors depending on the current Tab.
     * @param selectedTab tab currently selected bu user
     */
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

    /**
     * Calls the method setSite from the Server fragment.
     * @param site selected website
     */
    public static void setSite(int site) {
        for(Fragment fragment : fm.getFragments()) {
            if(fragment instanceof Server) {
                Server server = (Server) fragment;
                server.setSite(site);
            }
        }
    }

    /**
     * @return current time (hour and minutes)
     */
    public static String printTime() {
        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format = new Formatter();
        format.format("%tl:%tM", gfg_calender, gfg_calender);

        return format.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

}