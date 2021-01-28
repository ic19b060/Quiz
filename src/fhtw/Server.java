package fhtw;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Server extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));

        primaryStage.setTitle("Login_Quiz");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(1111, 10);

        while (true) {
            try {

                System.out.println("Server waiting for connection");
                Socket client = server.accept(); //warten bis Verbindung hergestellt wurde


                System.out.println("Just connected to " + client.getInetAddress());
                DataInputStream in = new DataInputStream(client.getInputStream());

                System.out.println(in.readUTF());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress());
                out.flush();

                ClientHandler clientSock
                        = new ClientHandler(client,in,out);

                // This thread will handle the client
                // separately
                new Thread(clientSock).start();


                //}  //while (true) {
                //            System.out.println("Server connected to " + client.getInetAddress());
                //            byte[] b = new byte[100]; //array mit 100 Stellen
                //            InputStream in = client.getInputStream();
                //
                //
                //            //OutputStream out = client.getOutputStream();
                //
                //            //messages werden in bytes verschickt
                //            int bytes = in.read(b); //Rückgabewert = gelesene Bytes (int)
                //            System.out.println("received " + bytes);
                //            String s = new String(b);
                //            System.out.println("message: " + s);
                launch(args);
            } catch (Exception e) {
                server.close();
                e.printStackTrace();
            }

        }


    }

    static class ClientHandler extends Thread {

        final Socket server;
        final DataInputStream in;
        final DataOutputStream out;

        public ClientHandler(Socket server, DataInputStream in, DataOutputStream out) {
            this.server = server;
            this.in = in;
            this.out = out;
        }


        public void run() {

        }

    }
}