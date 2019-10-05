package com.wheejuni.webserver;

import java.io.IOException;
import java.net.ServerSocket;

public class WebServer {
    public static void main(String[] args) {
        ServerSocket connectionSocket = null;

        try {
            connectionSocket = new ServerSocket(8080);
        } catch (IOException e) {

        }
    }
}
