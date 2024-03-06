package day10.revision.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class HandleClient implements Runnable {

   private final Socket socket;
   private final LogSession logger;

   public HandleClient(Socket socket, LogSession logger) { 
      this.socket = socket; 
      this.logger = logger;
   }

   @Override
   public void run() {
      System.out.printf(">>> running on thread: %s\n", Thread.currentThread().getName());
      try {
         process();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void process() throws Exception {
      String line;

      logger.log("Client info: %s".formatted(socket.getInetAddress().toString()));

      // Create a Reader
      try (Reader reader = new InputStreamReader(socket.getInputStream());
            Writer writer = new OutputStreamWriter(socket.getOutputStream())) {

         BufferedReader br = new BufferedReader(reader);
         BufferedWriter bw = new BufferedWriter(writer);

         boolean quit = false;

         while (!quit) {
            line = br.readLine();

            quit = line.trim().toLowerCase().equals(Constants.QUIT);
            if (quit) 
               continue;

            if (Constants.FLUSH.equals(line.trim().toLowerCase())) {
               logger.flush();;
               continue;
            }

            System.out.printf(">>>> %s\n", line);
            logger.log(line);

            System.out.println("<<<< Writing to client");
            bw.write(line.toUpperCase() + "\n");
            bw.flush();
         }
      }

      System.out.println("Close client socket");
   }
   
}
