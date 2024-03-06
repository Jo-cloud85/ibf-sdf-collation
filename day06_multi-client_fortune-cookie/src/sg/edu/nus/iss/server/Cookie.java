package sg.edu.nus.iss.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cookie {
    public static String getRandomCookie(String cookieFilePath) {
        File cookieFile = new File (cookieFilePath);
        List<String> cookies = new LinkedList<>();
        String randomCookie = "";
        Scanner scanner = null;

        try {
            scanner = new Scanner(cookieFile);
            while(scanner.hasNextLine()) {
                cookies.add(scanner.nextLine());
            }
            //System.out.println(cookies);
            Random rand = new Random();
            randomCookie = cookies.get(rand.nextInt(cookies.size()));
            System.out.println(randomCookie);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } finally {
            System.out.println("Close");
        }
        
        return randomCookie;
    }
}
