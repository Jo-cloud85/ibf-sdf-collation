package main.java.sg.edu.nus.iss.assessment.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 1. javac -d bin src/main/java/sg/edu/nus/iss/assessment/client/* src/main/java/sg/edu/nus/iss/assessment/server/*
// 2. java -cp bin main.java.sg.edu.nus.iss.assessment.server.ServerApp 80

public class ServerApp {
    private static Pattern pattern;
    private static Matcher matcher;

    private static final String EMAIL_PATTERN 
        = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        
    public static void main(String[] args) {
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;
        ServerSocket serverSocket = null;

        String serverPort = args[0];

        try {
            // Create a server
            serverSocket = new ServerSocket(Integer.parseInt(serverPort));
            System.out.println("Server running at port " + serverPort + "...");
            pattern = Pattern.compile(EMAIL_PATTERN);
            
            socket = serverSocket.accept();
            
            os = socket.getOutputStream();
            ObjectOutputStream dos =new ObjectOutputStream(os);
            dos.writeObject("1234abcd 97,35,82,2,45");

            is = socket.getInputStream();
            //DataInputStream dis = new DataInputStream(is);
            ObjectInputStream dis = new ObjectInputStream(is);

            while(true){
                
                try{
                    // String requestId = dis.readUTF();
                    // String name = dis.readUTF();
                    // String email = dis.readUTF();
                    // float average = dis.readFloat();
                    String requestId = (String)dis.readObject();
                    String name = (String)dis.readObject();
                    String email = (String)dis.readObject();
                    Float average = (Float)dis.readObject();
                    System.out.println("Request Id: " + requestId);
                    System.out.println("Name: " + name);
                    System.out.println("Email: " + email);
                    System.out.println("Average: " + average);
                    System.out.println(average);
                    matcher = pattern.matcher(email);
                    boolean isEmailOk = matcher.matches();
                    System.out.println(isEmailOk);
                    if(average != 52.2f  || isEmailOk == false){
                        dos.writeObject(false);
                    }else{
                        dos.writeObject(true);
                    }
                }catch(EOFException e){
                    break;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                is.close();
                os.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
    }
}
