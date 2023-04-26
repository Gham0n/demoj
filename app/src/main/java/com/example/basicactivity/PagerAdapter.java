package com.example.basicactivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.basicactivity.fragments.*;

public class PagerAdapter extends FragmentStateAdapter {

    // Constructeur de la classe PagerAdapter
    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // Cette méthode est appelée lorsqu'un fragment doit être créé
    @NonNull
    @Override
    public Fragment createFragment(int index) {
        switch (index) {
            case 1:
                return new Network(); // retourne un nouvel objet Network()
            case 2:
                return new Server(); // retourne un nouvel objet Server()
            default:
                return new Terminal(); // retourne un nouvel objet Terminal()
        }
    }

    // Cette méthode retourne le nombre total de fragments (onglets) gérés par le PagerAdapter
    @Override
    public int getItemCount() {
        return 3; // retourne le nombre total de fragments (onglets)
    }

}
