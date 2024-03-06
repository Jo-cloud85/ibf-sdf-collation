package day10.revision.networking;

import java.net.Socket;

public class NetClient {

   public static void main(String[] args) throws Exception {

      // Connect to the server
      try (Socket socket = new Socket("localhost", 3000)) {
         System.out.printf("We hava a connection to the server\n");

         ClientSession sess = new ClientSession(socket);
         sess.process();

      }

      // exit the block, the socket is closed: socket.close()
   }
   
}
