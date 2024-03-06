package main.java.sdfassessment.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

public class MailMerge {
    
    // create single mail file for each firstName, with the template copied in
    public static File createSingleMailFile (String firstName, Path templatePath) {
        try {
            Path singleMailPath = Paths.get("dataDirectory" + "/" + firstName + ".txt" );

            if (!Files.exists(singleMailPath)) {
                Files.createFile(singleMailPath);
            }

            File singleMailFile = 
                Files.copy(templatePath, singleMailPath, StandardCopyOption.REPLACE_EXISTING).toFile();

            return singleMailFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    // read and replace each line of the mail file with the data from the csv file
    public static void searchAndReplace(File singleMailFile, Person person, Map<String, String> variableMap) {
        // create a buffer reader to read through the lines of the single mail file
        try (BufferedReader br = new BufferedReader(new FileReader(singleMailFile))) {
            String newContent = "";
            String line;
            FileWriter writer = null;
            while ((line = br.readLine()) != null){
                String replacedLine = replaceVariables(line, variableMap);
                newContent += replacedLine + System.lineSeparator();
            }

            System.out.println(newContent);
            System.out.println("-".repeat(20));

            writer = new FileWriter(singleMailFile);
            writer.write(newContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String replaceVariables(String templateLine, Map<String, String> variableMap) {
        for (Map.Entry<String, String> entry : variableMap.entrySet()) {
            if (entry.getValue().contains("\\n")) {
                entry.setValue(entry.getValue().replace("\\n", "\n"));
            }
            templateLine = templateLine.replace(entry.getKey(), entry.getValue());
        }
        return templateLine;
    }
}