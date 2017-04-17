package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by admin on 17.04.2017.
 */
public class SenderThread extends Thread {
    private Thread s;
    private static Socket socket;
    private static BufferedWriter bufferedWriter;
    public boolean stop = false;

    public SenderThread() {
        s = new Thread(this);
        socket = null;
        try {
            socket = new Socket("localhost", 5555);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            while (!stop) {
                bufferedWriter.write("Hello from client!");
                bufferedWriter.flush();
            }

            socket.close();
            Thread.yield();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
