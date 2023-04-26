package com.example.basicactivity;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ping {

    static String TAG = "log du cmd";

    // Cette méthode prend en argument une URL à "pinger" et retourne une chaîne de caractères contenant les résultats de la commande ping
    public static String ping(String url)
    {
        String str = "";
        try {
            // Exécute la commande ping dans le terminal de l'appareil Android en utilisant Runtime.getRuntime().exec()
            Process process = Runtime.getRuntime().exec(
                    "/system/bin/ping -c 8 " + url);

            // Stocke le résultat de la commande ping dans un objet BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            int i;
            char[] buffer = new char[4096];
            StringBuilder output = new StringBuilder();

            // Boucle pour lire les résultats de la commande ping stockés dans l'objet BufferedReader
            while ((i = reader.read(buffer)) > 0)
                output.append(buffer, 0, i);
            reader.close();

            // Stocke les résultats dans un objet StringBuilder
            str = output.toString();

            // Enregistre les résultats de la commande ping dans les logs de l'application
            Log.d(TAG, str);
        } catch (IOException e) {
            // Affiche une erreur si la commande ping ne peut pas être exécutée
            e.printStackTrace();
        }

        // Renvoie les résultats de la commande ping sous forme de chaîne de caractères
        return str;
    }
}
