package main.java.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// 1. javac -d bin src/main/java/client/* src/main/java/server/*  
// (just run this once either at client or server terminal everytime there is changes)

// 2. java -cp bin main.java.client.ClientApp localhost:12345

// Make sure server is up first, then run up the client

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("ClientApp.main()");
        String[] connInfo = args[0].split(":");
        System.out.println(connInfo[0] + " " + connInfo[1]);

        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        boolean quit = false;

        try {
            socket = new Socket(connInfo[0], Integer.parseInt(connInfo[1]));
                
            is = socket.getInputStream();
            DataInputStream dis = new DataInputStream(is);

            os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Console cons = System.console();

            while (!quit) {
                String input = cons.readLine("Send client command to the server: ");

                dos.writeUTF(input);
                dos.flush();

                if (input.trim().toLowerCase().equals("quit")) {
                    quit = true;
                    continue;
                }

                String response = dis.readUTF();

                if (response.contains("cookie-text_")) {
                    String[] arrRes = response.split("_");
                    System.out.println("Cookie from server: " + arrRes[1]);
                } else {
                    System.err.println(response);
                }

                // is.close();
                // os.close();
            }
            socket.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}