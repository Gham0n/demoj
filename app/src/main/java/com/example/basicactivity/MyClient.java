package com.example.basicactivity;

import java.net.*;
import java.io.*;

public class MyClient {
    public static String data = "Empty";
    public static Boolean change = false; // Indicates whether or not data has changed since last iteration
    public static Boolean isConnected = false; // Client connected or not

    public static void launchClient() {
        Thread thread = new Thread(() -> {
            try  {
                // Connexion au serveur
                // Socket s = new Socket("10.0.2.2",3333); //localhost
                Socket socket = new Socket("10.3.141.1",3333);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                System.out.println("Connected to server.");
                isConnected = true;

                while(true){
                    if(change) {
                        System.out.println("data : " + data);
                        out.writeUTF(data); // Send data to server
                        out.flush();

                        // Stops connexion
                        if(data.equals("stop")){
                            isConnected=false;
                            data ="Empty";
                            break;
                        }

                        change = false;
                    }
                }

                out.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Failed to connect to server.");
            }
        });

        thread.start();

    }

    public static Boolean getIsConnected() {
        return isConnected;
    }

    /**
     * Define new data to be sent and signals change
     * @param data data to be sent
     * @return data sent
     */
    public static String setStr(String data) {
        MyClient.data = data;
        change = true;
        return data;
    }
}
