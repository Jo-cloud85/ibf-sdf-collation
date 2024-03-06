package main.java.sg.edu.nus.iss.assessment.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

// 1. javac -d bin src/main/java/sg/edu/nus/iss/assessment/client/* src/main/java/sg/edu/nus/iss/assessment/server/*
// 2. java -cp bin main.java.sg.edu.nus.iss.assessment.client.ClientApp localhost:80

public class ClientApp {
    public static void main(String[] args) {
        // System.out.println("ClientApp.main()");
        String[] connInfo = args[0].split(":");
        System.out.println(connInfo[0] + " " + connInfo[1]);

        Socket sock;
        String requestId;
        String email = "x@x.com";
        String name = "Kenneth Phang Tak Yan";
        float average = 0;
        int totalSum = 0;
        float howManynos = 0;

        try {
            sock = new Socket(connInfo[0], Integer.parseInt(connInfo[1]));

            // ** The task calls to get the OutputStream first **

            OutputStream os = sock.getOutputStream();
            //DataOutputStream dos = new DataOutputStream(os);
            ObjectOutputStream oos = new ObjectOutputStream(os);
        
            InputStream is = sock.getInputStream();
            //DataInputStream dis = new DataInputStream(is);
            ObjectInputStream ois = new ObjectInputStream(is);

            String response = (String) ois.readObject();
            System.out.println(response);

            String[] splittedResult = response.split(" ");
            requestId = splittedResult[0];

            String[] averageNos = splittedResult[1].split(",");
            howManynos = averageNos.length;
            for (String no : averageNos) {
                totalSum += Integer.parseInt(no);
            }
            average = totalSum / howManynos;
            System.out.println(average);

            // dos.writeUTF(requestId);
            // dos.writeUTF(name);
            // dos.writeUTF(email);
            // dos.writeFloat(average);
            oos.writeObject(requestId);
            oos.writeObject(name);
            oos.writeObject(email);
            oos.writeObject(average);
            
            Boolean isOk = (Boolean) ois.readObject();
            System.out.println(isOk);

            if(isOk){
                System.out.println("SUCCESS");
            }else{
                System.out.println("FAILED");
            }
            sock.close();
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
