package sg.edu.nus.iss.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 1. javac -d classes src/sg/edu/nus/iss/client/* src/sg/edu/nus/iss/server/*
// 2. java -cp classes sg.edu.nus.iss.server.ServerApp 12345 cookie_file.txt 

public class ServerApp {
    public static void main(String[] args) {

        int portNumber = 3000;

        if(args.length > 0 && Integer.parseInt(args[0]) != 0)
            portNumber = Integer.parseInt(args[0]);

        String cookieFile = args[1];

        // The server defines the threadpool whereas the thread comes from the client
        ExecutorService threadpool = Executors.newFixedThreadPool(2);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket socket = serverSocket.accept();
                CookieClientHandler cch = new CookieClientHandler(socket, cookieFile);
                threadpool.submit(cch); // don't use execute()
                System.out.println("Submitted to threadpool");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
