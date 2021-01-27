
package fhtw;
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try{
            ServerSocket server = new ServerSocket(1111, 10);
            System.out.println("Server waiting for connection");
            Socket client = server.accept(); //warten bis Verbindung hergestellt wurde

            //while (true) {
                System.out.println("Server connected to " + client.getInetAddress());
                byte[] b = new byte[100]; //array mit 100 Stellen
                InputStream in = client.getInputStream();


                //OutputStream out = client.getOutputStream();

                //messages werden in bytes verschickt
                int bytes = in.read(b); //Rückgabewert = gelesene Bytes (int)
                System.out.println("received " + bytes);
                String s = new String(b);
                System.out.println("message: " + s);

            //}
        } catch (Exception e){
        }
    }
}
