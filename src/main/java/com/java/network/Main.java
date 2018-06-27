package com.java.network;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        String host = "127.0.0.1";
        int port = 2345;

        TimeServer ts = new TimeServer(host, port);
        ts.open();

        System.out.println("Serveur initialise.");

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new ClientConnexion(host, port));
            t.start();
        }
        new ClientConnexion(host, port);

    }

}
