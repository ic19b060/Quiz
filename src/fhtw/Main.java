package fhtw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;

//import static fhtw.Link.Link;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));

        primaryStage.setTitle("Login_Quiz");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    public static void main(String[] args) {

        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection collections = db.getCollection("CustomGame");

            CustomQuestionCollection cur = new CustomQuestionCollection();
            cur.setName("1");
            ArrayList<Question> objects = new ArrayList<>();
            Question question = new Question();
            question.setCorrectAnswer("ad");
            question.setIncorrectAnswers(Arrays.asList("b", "c", "d"));
            question.setQuestion("Frage");
            objects.add(question);
            cur.setQuestions(objects);

            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonString = gson.toJson(cur);
            collections.insertOne(Document.parse(jsonString));

            BasicDBObject criteria = new BasicDBObject();
            criteria.append("name", "1");
            FindIterable<Document> cur_profile = collections.find(criteria);
            for (Document document : cur_profile) {
                CustomQuestionCollection v = gson.fromJson(document.toJson(), CustomQuestionCollection.class);
                System.out.println(v);
            }

            //collections.find("Name", name);

        }

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

