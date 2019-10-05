package com.wheejuni.webserver.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

public class RequestListener implements Runnable {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    private Socket requestSocket;

    public RequestListener(Socket requestSocket) {
        this.requestSocket = requestSocket;
    }

    public void run() {
        StringBuilder requestBody = new StringBuilder();
        String line = null;

        log.debug("new client connect: IP: {}, port: {}", requestSocket.getInetAddress(), requestSocket.getPort());
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream(), "UTF-8"));
            while((line = in.readLine()) != null){
                log.debug(line);
                requestBody.append(line);

                if (line.equalsIgnoreCase("\n")) {
                    break;
                }
            }
        } catch (IOException e) {
            log.error("error while getting input stream from incoming connection: {}", e);
        }
        log.info("{}", requestBody);
    }
}
