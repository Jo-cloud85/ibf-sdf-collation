package sg.edu.nus.iss.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

// 1. java -cp classes sg.edu.nus.iss.client.ClientApp localhost:12345

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("ClientApp running...");

        // Processing the arg input which is localhost:12345 when we run this file
        String[] connInfo = args[0].split(":");
        System.out.println(connInfo[0] + " " + connInfo[1]);

        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        boolean quit = false;
        
        try {
            // 1st param is the hosting address which is connInfo[0] which is localhost
            // 2nd param is the port
            socket = new Socket(connInfo[0], Integer.parseInt(connInfo[1]));

            // input from the server
            is = socket.getInputStream();
            DataInputStream dis  = new DataInputStream(is);

            // output from the client back to the server
            os = socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);

            Console cons = System.console();

            while (!quit) {
                String input = cons.readLine("Server command to the server: ");

                // Writes the input from console to the output stream to be sent back to the server
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
            }
            // is.close();
            // os.close();
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
