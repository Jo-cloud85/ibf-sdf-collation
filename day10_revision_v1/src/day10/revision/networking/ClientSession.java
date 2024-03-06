package day10.revision.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;

public class ClientSession {

   private final Socket socket;

   public ClientSession(Socket socket) {
      this.socket = socket;
   }

   public void process() throws Exception {

      Writer writer = new OutputStreamWriter(socket.getOutputStream());
      System.out.println(">>>> Writing to Server");
      BufferedWriter bw = new BufferedWriter(writer);

      Reader reader = new InputStreamReader(socket.getInputStream());
      BufferedReader br = new BufferedReader(reader);

      boolean quit = false;
      // Get a line from the Console
      Console cons = System.console();

      while (!quit) {
         String line = cons.readLine("Please enter a message: ");

         // Send the line to the server
         bw.write(line + "\n");
         bw.flush();
         writer.flush();

         // Check if we should quit
         quit = line.trim().toLowerCase().equals(Constants.QUIT);
         if (quit) 
            continue;

         // Read response from server
         line = br.readLine();
         System.out.printf("<<<< FROM SERVER: %s\n", line);
      }

      reader.close();
      writer.close();

      socket.close();

      System.out.println("Close socket connection");

   }
}
