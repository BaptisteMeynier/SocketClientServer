package com.java.network;

import java.io.IOException;
import java.net.ServerSocket;

public class PortFinder {

    public static void main(String[] args) {
        for (int port = 1; port <= 65535; port++) {

            try (ServerSocket ignored = new ServerSocket(port)) {

            } catch (IOException e) {
                System.err.println("Le port " + port + " est deja utilise ! ");
            }
        }
    }
}

