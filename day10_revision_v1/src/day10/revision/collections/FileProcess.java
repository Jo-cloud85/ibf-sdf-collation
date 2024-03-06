package day10.revision.collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Optional;

public class FileProcess {

   public static void main(String[] args) throws Exception {
      //imperative(args[0]);
      functional(args[0]);
   }

   public static void functional(String fn) throws Exception {
      try (FileReader fr = new FileReader(fn)) {
         BufferedReader br = new BufferedReader(fr);

         //long count = 
         Optional<Integer> opt = br.lines()
            .map(line -> line.replaceAll("\\p{Punct}", "")) // string -> string
            .map(line -> line.split(" ")) // string -> string[]
            .filter(words -> words.length >= 7)
            .map(words -> words.length)
            .reduce((acc, count) -> acc + count);
            //.count();

         System.out.printf("Total number of words: %d\n", opt.get());
      }

   }

   public static void imperative(String fn) throws Exception {
      try (FileReader fr = new FileReader(fn)) {
         BufferedReader br = new BufferedReader(fr);
         String line;
         int total = 0;
         while (null != (line = br.readLine())) {

            //  VVVV
            // map string -> string
            line = line.replaceAll("\\p{Punct}", "");

            // map string -> string[]
            String[] words = line.split(" ");

            // filtering  
            if (words.length < 7)
               continue;

            System.out.printf(">>> line: %s (%d)\n", line, words.length);
            // reduce
            total++;
         }
         System.out.printf("lines: %d\n", total);
      }

   }
}
