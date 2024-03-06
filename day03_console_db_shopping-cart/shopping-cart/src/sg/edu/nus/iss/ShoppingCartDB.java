package sg.edu.nus.iss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDB {
    private File repository;

    public ShoppingCartDB(String _repository) {
        this.repository = new File(_repository);
    }

    public File getRepository() {
        return repository;
    }

    public void setRepository(File repository) {
        this.repository = repository;
    }

    public List<String> listUsers(){
        List<String> users = new LinkedList<String>();
        if (repository.listFiles() != null) {
            for(File cartFile: repository.listFiles()){
                users.add(cartFile.getName().replace(".db", ""));
            }
        } else {
            System.out.println("No registered users yet");
        }
        return users;
    }

    // What happens in the repo/database when user wants to load file in the console
    public ShoppingCart load(String username){
        String cartName = username + ".db";
        ShoppingCart cart = new ShoppingCart(username);

        // Always check if directory exists first, then go into the file(s)
        File cartDB = new File(repository.getPath());
        if(!cartDB.exists()){
            try{
                Path p = Paths.get(repository.getPath());
                Files.createDirectory(p);
            } catch(IOException e){
                System.err.println("File already exists: " + e.getMessage());
            }
        }

        for(File cartFile: repository.listFiles()){
            if(cartFile.getName().equals(cartName)){
                InputStream is;
                try {
                    is = new FileInputStream(cartFile);
                    cart.load(is);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return cart;
    }

    // What happens in the repo/database when user want to save from console to file
    public void save(ShoppingCart cart) throws IOException {
        // create file name first
        String cartDBfilename = cart.getUsername() + ".db";
        // create the path for the file
        String savedbLocation = repository.getPath() + File.separator + cartDBfilename;
        // try to find a db file based on the path first
        File cartDB = new File(savedbLocation);
        OutputStream os = null;
        try{
            // if cannot find, create a directory first, then create the db file inside
            if(!cartDB.exists()){
                System.out.println("Looks like you are a new user! Creating an account for you...");
                cartDB.createNewFile();
            }
            System.out.println("Saving to your account");
            os = new FileOutputStream(savedbLocation);
            // the save method in ShoppingCart class takes in an OutputStream from the file
            // and create a writer to write things from the console to the file
            cart.save(os);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            os.flush();
            os.close();
        }
    }
}
