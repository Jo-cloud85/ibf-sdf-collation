package main.java.sdfassessment.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

// 1. javac -d bin src/main/java/sdfassessment/task01/*
// 2. java -cp bin main.java.sdfassessment.task01.Main data.csv template.txt

public class Main {

    public static void main(String[] args) {

        String csvFile = null;
        String templateFile = null;


        // check for args
        if (args.length != 2) {
            System.out.println("Please enter <CSV file> and <template file>");
        } else {
            csvFile = args[0];
            templateFile = args[1];
        }  
        

        // try to get the path of the csv and template file
        Path csvPath = Paths.get(csvFile);
        Path templatePath = Paths.get(templateFile);
        // check if there is a file in this path, if doesn't exist, print out message
        if (!Files.exists(csvPath)) {
            System.out.println("CSV file cannot be found");
        } 
        
        if (!Files.exists(templatePath)) {
            System.out.println("Template file cannot be found");
        }


        // try to get the path of the directory for mail letters
        Path dataDir = Paths.get("dataDirectory");
        // if a directory in this path does not exist, create the directory
        if (!Files.exists(dataDir)) {
            try {
                Files.createDirectories(dataDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } 

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvPath.toFile()));
            String headerLine = br.readLine(); // for reading header first
            String[] columnNamesArray = headerLine.split(","); // get an array of all column names

            String row;

            // Reading each line of csv file, other than the header
            while ((row = br.readLine()) != null) {
                String[] personArray =  row.split(","); // [Sherlock, Holmes, 221 Baker Street\nLondon, 22]

                // Create an empty map
                Map<String, String> personMap = new HashMap<>(); 

                // Start adding key-value pairs into the map like this [__first_name__: Sherlock, ...]
                for (int i=0; i<columnNamesArray.length; i++) {
                    personMap.put("__" + columnNamesArray[i] + "__", personArray[i]);
                }
                
                // Create a file for each of the person using their firstNames
                File singleMailFile = MailMerge.createSingleMailFile(personArray[0], templatePath);

                Person person = new Person(
                    personArray[0], 
                    personArray[1], 
                    personArray[2], 
                    Integer.parseInt(personArray[3]));

                MailMerge.searchAndReplace(singleMailFile, person, personMap);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}