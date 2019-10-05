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
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream(), "UTF-8"));
            while((line = in.readLine()).equalsIgnoreCase("")){
                System.out.println(line);
                requestBody.append(line);
            }
        } catch (IOException e) {
            log.error("error while getting input stream from incoming connection: {}", e);
        }

        try( BufferedWriter out = new BufferedWriter(new OutputStreamWriter(requestSocket.getOutputStream()))) {
            out.write("hello this is server");
        } catch (IOException e) {
            log.error("error while writing response: {}", e);
        }
        System.out.println(requestBody);
    }
}
