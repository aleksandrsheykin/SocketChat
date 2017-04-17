package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 17.04.2017.
 */
public class HandlerThread extends Thread {
    private Thread s;
    private static Socket socket;
    private static ServerSocket serverSocket;
    private static BufferedReader bufferedReader;
    static String msg;
    public boolean stop = false;

    public HandlerThread() {
        s = new Thread(this);
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5555);
            socket = serverSocket.accept();
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            msg = null;
            while (!stop) {
                msg = bufferedReader.readLine();
                if (msg == null)
                    System.out.println(msg);
            }
            socket.close();
            Thread.yield();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
