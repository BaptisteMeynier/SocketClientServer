package com.java.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeServer {

    //On initialise des valeurs par defaut
    private int port = 2345;
    private String host = "127.0.0.1";
    private ServerSocket server = null;
    private boolean isRunning = true;
    private int fileAttente= 100;

    public TimeServer(){
        try {
            server = new ServerSocket(port, fileAttente, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TimeServer(String pHost, int pPort){
        host = pHost;
        port = pPort;
        try {
            server = new ServerSocket(port, fileAttente, InetAddress.getByName(host));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //On lance notre serveur
    public void open(){

        //Toujours dans un thread a part vu qu il est dans une boucle infinie
        Thread t = new Thread(() -> {
            while(isRunning == true){

                try {
                    //On attend une connexion d'un client
                    Socket client = server.accept();

                    //Une fois recue, on la traite dans un thread separe
                    System.out.println("Connexion cliente reçue.");
                    Thread t1 = new Thread(new ClientProcessor(client));
                    t1.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                server = null;
            }
        });

        t.start();
    }

    public void close(){
        isRunning = false;
    }
}
