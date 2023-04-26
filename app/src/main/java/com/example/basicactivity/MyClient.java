package com.example.basicactivity;

import java.net.*;
import java.io.*;

public class MyClient {

    // Déclaration des variables statiques
    public static String str = "Empty"; // Chaîne de caractères initiale
    public static Boolean change= false; // Indique si la chaîne de caractères a changé depuis la dernière itération
    public static Boolean isConnected =false; // Indique si le client est connecté au serveur

    // Méthode pour lancer le client
    public static void launchClient(){

        Thread thread = new Thread(() -> {
            try  {
                // Connexion au serveur
                //Socket s=new Socket("10.0.2.2",3333); //localhost
                Socket s=new Socket("10.3.141.1",3333);       //Vega
                DataOutputStream dout=new DataOutputStream(s.getOutputStream());

                // Affichage d'un message de connexion réussie
                System.out.println("Connected to server.");
                isConnected=true;

                // Boucle principale pour envoyer des données au serveur
                while(true){

                    if(change) {
                        // Affichage de la chaîne de caractères actuelle
                        System.out.println("str = " + str);
                        dout.writeUTF(str); // Envoi de la chaîne de caractères au serveur
                        dout.flush();

                        // Si la chaîne de caractères est "stop", arrêter la connexion
                        if(str.equals("stop")){
                            isConnected=false;
                            str="Empty";
                            break;
                        }

                        change = false; // La chaîne de caractères a été envoyée
                    }
                }

                // Fermeture des flux de données et de la connexion
                dout.close();
                s.close();
            } catch (Exception e) {
                System.out.println("Failed to connect to server.");
            }
        });

        // Démarrage du thread pour le client
        thread.start();

    }

    // Méthode pour obtenir l'état de connexion du client
    public static Boolean getIsConnected() {
        return isConnected;
    }

    // Méthode pour définir la chaîne de caractères actuelle et signaler un changement
    public static String setStr(String str) {
        MyClient.str = str;
        change = true;
        return str;
    }

}
