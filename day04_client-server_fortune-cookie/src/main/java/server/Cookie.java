package main.java.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Cookie {

    public static String getRandomCookie(String cookieFilePath) {
        String randomCookie = "No cookie for you !";
        List<String> cookies = new LinkedList<>();

        try {
            cookies = getDataFromCookieFile(cookieFilePath);

            Random r = new Random();
            
            if (cookies.size() > 0) {
                int randomIndex = r.nextInt(cookies.size());
                randomCookie = cookies.get(randomIndex);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomCookie;
    }

    private static List<String> getDataFromCookieFile(String cookieFilePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(cookieFilePath));
        List<String> cookies = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            cookies.add(line);
        }
        br.close();
        return cookies;
    }
}
