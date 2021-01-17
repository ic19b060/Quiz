package fhtw;

/*package fhtw;
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try{
            ServerSocket server = new ServerSocket(1111);
            System.out.println("Server waiting for connection");
            Socket client = server.accept(); //warten bis Verbindung hergestellt wurde

            while (true) {
                System.out.println("Server connected to " + client.getInetAddress());
                byte[] b = new byte[100]; //array mit 100 Stellen

                InputStream in = client.getInputStream();
                OutputStream out = client.getOutputStream();

                //messages werden in bytes verschickt
                int bytes = in.read(b); //Rückgabewert = gelesene Bytes (int)
                System.out.println("received " + bytes);
                String s = new String(b);
                System.out.println("message: " + s);

            }
        } catch (Exception e){
        }
    }
}

*/


/*
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try{

            ServerSocket socket = new ServerSocket(1111);
            System.out.println("waiting for connection");
            Socket client = socket.accept(); //warten bis Verbindung hergestellt wurde
            System.out.println("connected to " + client.getInetAddress());


            while(true) {
                InputStream in = client.getInputStream();
                ;

                byte[] b = new byte[100]; //array mit 100 Stellen
                int bytes = in.read(b); //RÃ¼ckgabewert = gelesene Bytes (int)
                String received = new String(b);
                System.out.println("Message from client received " + received);
                System.out.println("Bytes from client:" + bytes);

                OutputStream out = client.getOutputStream();
                String retour = received;
                byte [] retourmessage = retour.getBytes();
                out.write(retourmessage);
                System.out.println("Message was sent to client: " + received);
                System.out.println("Bytes sent to client: " + bytes);
            }

        } catch (Exception e){




        }


    }
}

*/