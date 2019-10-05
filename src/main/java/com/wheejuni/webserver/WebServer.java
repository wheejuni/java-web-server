package com.wheejuni.webserver;

import com.wheejuni.webserver.listener.RequestListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    private static final Logger SERVER_CORE_LOGGER = LoggerFactory.getLogger("CORE-SERVER-MESSAGE");
    public static void main(String[] args) throws Exception {
        ServerSocket connectionSocket = null;

        try {
            connectionSocket = new ServerSocket(8080);
        } catch (IOException e) {
            SERVER_CORE_LOGGER.error("error while initiating port connection!");
        }
        SERVER_CORE_LOGGER.info("server started successfully on port 8080!");
        Socket incomingSocket = null;
        while((incomingSocket = connectionSocket.accept()) != null) {
            RequestListener listener = new RequestListener(incomingSocket);
            Thread requestThread = new Thread(listener);

            requestThread.start();
        }
    }
}
