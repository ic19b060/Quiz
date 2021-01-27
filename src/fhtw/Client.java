package fhtw;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Client extends Application {

    public static void main(String[] args) {


    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}




/*
package fhtw;
import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Client extends Application {
    TextArea txt_log = new TextArea();

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox topvbox = new VBox();
        topvbox.setPadding(new Insets(10));
        topvbox.setSpacing(10);
        BorderPane borderpoane = new BorderPane();
        Label lbl = new Label();
        lbl.setText("Enter a message you want to send to the server");
        TextArea txt_eingabe = new TextArea();
        txt_eingabe.setPromptText("Enter message");
        VBox vbox = new VBox();
        Button btn_add = new Button("Send message");
        btn_add.setPrefWidth(100);
        Button btn_clear = new Button("Clear");
        btn_clear.setPrefWidth(100);


        try {
            Socket client = new Socket("localhost", 1111);
            log("connected to " + client.getInetAddress());

            btn_add.setOnAction(e -> {

                try {


                    OutputStream out = client.getOutputStream();
                    //messages werden in bytes verschickt
                    String message = txt_eingabe.getText();
                    byte[] bmessage = message.getBytes();
                    out.write(bmessage);
                    log("Message was sent to Server");
                    txt_eingabe.setText(null);

                    InputStream in = client.getInputStream();
                    byte[] received = new byte[100]; //array mit 100 Stellen
                    int bytes = in.read(received);
                    String r = new String(received);
                    log("Message from server received " + r);
                    log("Bytes from server:" + bytes);


                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });


        } catch (Exception e){


        }



        btn_clear.setOnAction(e -> {
            txt_log.clear();
            txt_eingabe.setText(null);
        });


        topvbox.getChildren().addAll(txt_log,lbl, borderpoane);
        borderpoane.setCenter(vbox);
        vbox.getChildren().addAll(txt_eingabe, btn_add,btn_clear);
        Scene scene = new Scene(topvbox, 300, 275);
        primaryStage.setTitle("Server-Client");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void log(String s) {
        txt_log.appendText(s + "\n");
    }

}
/*
 */