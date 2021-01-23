package fhtw;

import com.google.gson.JsonObject;
import com.mongodb.Mongo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

//import static fhtw.Link.Link;
import static fhtw.APIReader.Json_complete;
import static fhtw.Gameplay.singleplay;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Login_Quiz");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {

      //  MongoDB.connect_to_db();
        //create link for question set based on user choice
        //connect with gui!
       // String link = Link();

        //create question set with created link for API
       // JsonObject questionsjson = Json_complete(link);

        //Gameplay logic
     //   List<Question> questions = Answers.parseQuestionJson(questionsjson);
       // QuestionRepository.getInstance().setQuestions(questions);
        //singleplay(questions);
        //multiplay(questions)
        launch(args);

    }


}

