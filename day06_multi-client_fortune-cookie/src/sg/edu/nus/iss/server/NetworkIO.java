package sg.edu.nus.iss.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// Technically, you can put this code all under ServerApp like what we did in the 1st version 
// of the fortune cookie app
// But now, we have a CookieClientHandler to manage this network of I/O streams for the server

public class NetworkIO {
    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public NetworkIO(Socket sock) throws IOException{
        is = sock.getInputStream();
        dis = new DataInputStream(is);
        os = sock.getOutputStream();
        dos = new DataOutputStream(os);
    }

    public String read() throws IOException {
        return dis.readUTF();
    }

    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
    }

    public void close(){
        try{
            dis.close();
            is.close();
            dos.close();
            os.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
