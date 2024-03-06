package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCart {
    private HashSet<String> contents  = new HashSet<>();
    private String username;

    public ShoppingCart(String _username){
        this.username = _username;
    }

    public List<String> getContents() {
        List<String> list = new LinkedList<String>(contents);
        return list;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void add(String item) {
        if(this.contents.contains(item))
            return;
        this.contents.add(item);
    }

    public void remove(String item) {
        if(this.contents.contains(item))
            contents.remove(item);
    }

    // Since you are getting/reading data from an external source, you need InputStream
    public void load(InputStream is) throws IOException{
        String item;
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(reader);
        while((item = br.readLine()) != null){
            contents.add(item); // rmb contents is the list on your console
        }
        br.close();
        reader.close();
    }

    // Since you are saving/writing data to an external resource, you need OutputStream
    public void save(OutputStream os){
        OutputStreamWriter writer = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(writer);
        try{
            // now writing each item of list from console into back to the file
            for(String item : contents){
                try{
                    bw.write(item); //item must be of type String
                    bw.newLine();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }finally{
            try{
                writer.flush();
                bw.flush();
                writer.close();
                bw.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
