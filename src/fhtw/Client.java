package fhtw;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {
        DataOutputStream out;
        Socket client = null;
        try {
            client = new Socket("localhost", 1111);
            System.out.println("Client connected to " + client.getInetAddress());


            System.out.println("Just connected to " + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            out.flush();

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);

            System.out.println("Server says " + in.readUTF());


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
