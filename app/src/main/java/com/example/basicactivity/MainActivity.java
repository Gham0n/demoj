package com.example.basicactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.basicactivity.fragments.Server;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.Formatter;
import java.util.Calendar;

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


        /*************************   POP-UP MENU   *************************/

        // New instance of PopupMenu
        PopupMenu popup = new PopupMenu(this, findViewById(R.id.menuBurger));

        // Ajouter les boutons supplémentaires
        popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        // Définir un écouteur de clic pour le FloatingActionButton
        findViewById(R.id.menuBurger).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Afficher le PopupMenu
                popup.show();
            }
        });

        // Définir un écouteur de clic pour les éléments de menu
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                // Vérifier si l'élément sélectionné est "Button 1"
                if (menuItem.getItemId() == R.id.btn_conncect) {
                    // Afficher le message de print
                    System.out.println("Connect button pushed at " + printHeure());
                    MyClient.launchClient();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(MyClient.getIsConnected())Toast.makeText(MainActivity.this, "Connecté au serveur.", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(MainActivity.this, "Erreur. Le serveur n'est pas démarré.", Toast.LENGTH_SHORT).show();
                    return true;
                }
                else  if (menuItem.getItemId() == R.id.btn_send) {
                    // Afficher le message de print
                    System.out.println("Send button pushed at " + printHeure());
                    MyClient.setStr("Blabla Test");
                    return true;
                }
                else  if (menuItem.getItemId() == R.id.btn_quit) {
                    // Afficher le message de print
                    System.out.println("Stop button pushed at " + printHeure());
                    MyClient.setStr("stop");
                    Toast.makeText(MainActivity.this, "Deconnexion.", Toast.LENGTH_SHORT).show();

                    return true;
                }
                return false;
            }
        });

        /*******************************************************************/

    }

    /**
     * Set different themes and colors depending on the current Tab.
     * @param selectedTab
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

    public static String printHeure() {
        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format = new Formatter();
        format.format("%tl:%tM", gfg_calender, gfg_calender);

        // Printing the current hour and minute
        return format.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public void onClickVega(View view) throws IOException, InterruptedException {
        System.out.println("Vega button pushed at " + printHeure());

        TextView tmp = (TextView) findViewById(R.id.logGallery);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nVega button pushed at " + printHeure() + "\n\n" + ping.ping("10.3.141.1"));
    }

    public void onClickDeneb(View view) throws IOException, InterruptedException {
        System.out.println("Deneb button pushed at " + printHeure());
        TextView tmp = (TextView) findViewById(R.id.logGallery);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nDeneb button pushed at " + printHeure() + "\n\n" + ping.ping("10.3.141.203"));
    }

    public void onClickAltair(View view) throws IOException, InterruptedException {
        System.out.println("Altaïr button pushed at " + printHeure());
        TextView tmp = (TextView) findViewById(R.id.logGallery);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nVega button pushed at " + printHeure() + "\n\n" + ping.ping("10.3.141.226"));
    }
/*
    public void onClickVegaConnect(View view) throws Exception {
        System.out.println("Connect button pushed at " + printHeure());

        MyClient.launchClient();
        TextView tmp = (TextView) findViewById(R.id.textVLog2);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nConnect button pushed at " + printHeure() + "\n");

    }

    public void onClickVegaSend(View view) throws IOException, InterruptedException {
        System.out.println("Send button pushed at " + printHeure());


        TextView tmp = (TextView) findViewById(R.id.textVLog2);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nSend button pushed at " + printHeure() + "\n " + MyClient.setStr("Send message blabla"));
    }


    public void onClickVegaStop(View view) throws IOException, InterruptedException {
        System.out.println("Stop button pushed at " + printHeure());


        TextView tmp = (TextView) findViewById(R.id.textVLog2);
        String res = (String) tmp.getText();
        tmp.setText(res + "\nStop button pushed at " + printHeure() + "\n " + MyClient.setStr("stop"));


    }*/

}