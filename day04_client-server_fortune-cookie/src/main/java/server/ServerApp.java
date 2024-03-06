package main.java.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 1. javac -d bin src/main/java/client/* src/main/java/server/* 
// (just run this once either at client or server terminal everytime there is changes)

// 2. java -cp bin main.java.server.ServerApp 12345 cookie_file.txt

// Make sure server is up first

public class ServerApp {
    
    public static void main(String[] args) {
        System.out.println("ServerApp.main()");

        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        ServerSocket serverSocket = null;
        boolean quit = false;

        try {
            String serverPort = args[0];
            String cookieFile = args[1];
            System.out.println("" + serverPort + " " + cookieFile);
        
            // Create a server
            serverSocket = new ServerSocket(Integer.parseInt(serverPort));
            System.out.println("Cookie Server started on " + serverPort);

            //while(!quit){
                System.out.println("Waiting for client...");
                // accept connections
                socket = serverSocket.accept();

                is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = socket.getOutputStream();
                DataOutputStream dos =new DataOutputStream(os);

                while(!quit){
                    System.out.println("received command from client");
                    try{
                        String dataFromClient =  dis.readUTF();

                        if(dataFromClient.equals("get-cookie")){
                            String cookieName = Cookie.getRandomCookie(cookieFile);
                            System.out.println("Sending cookie: " + cookieName);
                            dos.writeUTF("cookie-text_" + cookieName);
                            dos.flush();
                        } 

                        else if(dataFromClient.equals("quit")){
                            System.out.println("Client requested to close connection");
                            dos.writeUTF("Goodbye");
                            dos.flush();
                            socket.close();
                            quit = true;
                            break;
                        } 
                        
                        else {
                            dos.writeUTF("Invalid command");
                            dos.flush();
                        }
                    }catch(EOFException e){
                        System.out.println("Client disconnected");
                        socket.close();
                        break;
                    }
                }
            //}
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }   
}
