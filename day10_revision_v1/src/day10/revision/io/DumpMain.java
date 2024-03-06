package day10.revision.io;

public class DumpMain {
   
   public static void main(String[] args) {
      if (args.length <= 0) {
         System.err.printf("Please provide a file\n");
         System.exit(-1);
      }

      DumpFile dump = new DumpFile(args[0]);
      dump.printLines();
      
   }
}
