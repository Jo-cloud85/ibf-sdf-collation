package day10.revision.networking;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;

public class LogSession {

   private Writer writer;
   private BufferedWriter bw;

   //public LogSession(String file) throws Exception {
   public LogSession(OutputStream os) throws Exception {
      // writer = new FileWriter(file);
      writer = new OutputStreamWriter(os);
      bw = new BufferedWriter(writer);
   }
   
   public void log(String line) throws Exception {
      Date timestamp = new Date();
      bw.write("[%s] %s\n".formatted(timestamp.toString(), line));
   }

   public void flush() throws Exception {
      bw.flush();
      writer.flush();
   }

   public void close() throws Exception {
      bw.flush();
      bw.close();
      writer.flush();
      writer.close();
   }
   
}
