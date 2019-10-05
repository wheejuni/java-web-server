package com.wheejuni.webserver.listener;

import com.wheejuni.webserver.http.HttpHeaders;
import com.wheejuni.webserver.http.RequestLine;
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
        StringBuilder requestHeader = new StringBuilder();
        StringBuilder requestBody = new StringBuilder();
        String line = null;
        RequestLine requestLine = null;
        HttpHeaders headers = null;

        log.debug("new client connect: IP: {}, port: {}", requestSocket.getInetAddress(), requestSocket.getPort());
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(requestSocket.getInputStream(), "UTF-8"));
            requestLine = new RequestLine(in.readLine());
            log.debug("request line: {}", requestLine);

            while((line = in.readLine()) != null){
                log.debug(line);
                requestHeader.append(line);
                requestHeader.append("\n");

                if (line.equalsIgnoreCase("\n")) {
                    break;
                }
            }
            headers = new HttpHeaders(requestHeader.toString());
        } catch (IOException e) {
            log.error("error while getting input stream from incoming connection: {}", e);
        }
        log.debug("generated headers: {}", requestHeader);
    }
}
