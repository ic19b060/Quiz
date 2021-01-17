package fhtw;

import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static fhtw.QuestionHandle.Link;
import static fhtw.APIHandle.Json_complete;
import static fhtw.gameplay.play;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Quiz");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }


    public static void main(String[] args) {

        //create link for question set based on user choice
        //connect with gui!
        String link = Link();

        //create question set with created link for API
        JsonObject questions = Json_complete(link);

        //gameplay logic
        play(questions);
        launch(args);

    }


}

