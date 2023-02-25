package com.example.basicactivity;
import java.net.*;
import java.io.*;
public class MyClient {

    public static String str = "Empty";
    public static Boolean change= false;

    public static void launchClient(){

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    Socket s=new Socket("10.0.2.2",3333); //localhost
                    //Socket s=new Socket("10.3.141.1",3333);       //Vega
                    DataOutputStream dout=new DataOutputStream(s.getOutputStream());



                    while(!str.equals("stop")){

                        if(change) {

                            dout.writeUTF(str);
                            dout.flush();


                            change = false;
                        }
                    }

                    dout.close();
                    s.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();

    }


    public static String getStr() {
        return str;
    }

    public static String setStr(String str) {
        MyClient.str = str;
        change = true;
        return str;
    }




}
/* Socket s=new Socket("10.3.141.1",3333);
        DataInputStream din=new DataInputStream(s.getInputStream());
        DataOutputStream dout=new DataOutputStream(s.getOutputStream());
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        String str="",str2="";
        while(!str.equals("stop")){
            str=br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2=din.readUTF();
            System.out.println("Server says: "+str2);
        }

        dout.close();
        s.close();
        */