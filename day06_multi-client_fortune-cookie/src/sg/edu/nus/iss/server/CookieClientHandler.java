package sg.edu.nus.iss.server;

import java.net.Socket;

// subprocess
// this handler is the thread that is spawned by the main thread
// simply put, this handler helps the Server to handle the random selection of the cookie and client
// connection must come in from client and connect to the server first
// then it manages the network of I/O streams from the server side where it helps the server to look
// out for what the Client input is/returns and return the appropriate response

public class CookieClientHandler implements Runnable {

    private Socket sock;
    private String cookieFile;

    public CookieClientHandler(Socket socket, String cookieFile) {
        this.sock = socket;
        this.cookieFile = cookieFile;
    }

    // The pre-requisite for this run() to get evoked and for the thread to get established is only
    // when the connection between client and server is established

    @Override
    public void run() {
        System.out.println("Starting a client thread...");
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().threadId());

        NetworkIO nio = null;
        String req = "";
        boolean quit = false;

        // creating an instance of the a network of I/O streams using a socket
        try {
            nio = new NetworkIO(sock);
            while (!quit) {
                req = nio.read();
                if (req.equals("quit")) {
                    System.out.println("Client disconnecting...");
                    quit = true;
                    break;
                } else if (req.trim().equals("get-cookie")) {
                    String cookie = Cookie.getRandomCookie(cookieFile);
                    nio.write("Cookie-text: " + cookie);
                    //break;
                } else {
                    nio.write("Error, invalid request");
                    quit = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
