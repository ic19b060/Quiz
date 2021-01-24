package fhtw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;

public class ControllerCreateQuestions {

       @FXML
        MenueController menueController;

        @FXML
        private TextField questionfield;

        @FXML
        private TextField correctAnswer;

        @FXML
        private TextField incorrectAnswer1;

        @FXML
        private TextField incorrectAnswer2;

        @FXML
        private TextField incorrectAnswer3;

        @FXML
        private Button AddQuestion;

        @FXML
        private Button createPackage;

        @FXML
        private Button cancel;

        @FXML
        void addQuestion(ActionEvent event) {

            try (MongoClient client = MongoDB.connect_to_db()) {
                MongoDatabase db = MongoDB.getDB(client);
                MongoCollection collections = db.getCollection("CustomGame");

                CustomQuestionCollection cur = new CustomQuestionCollection();
                cur.setName("1");
                ArrayList<Question> objects = new ArrayList<>();
                Question question = new Question();
                question.setCorrect_answer("ad");
                question.setIncorrect_answers(Arrays.asList("b", "c", "d"));
                question.setQuestion("Frage");
                objects.add(question);
                cur.setQuestions(objects);

                Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
                String jsonString = gson.toJson(cur);
                collections.insertOne(Document.parse(jsonString));

                //connect to database
       /*     try (MongoClient client = MongoDB.connect_to_db()) {
                MongoDatabase db = MongoDB.getDB(client);

                MongoCollection<Document> custom_question_collection = createCustomQuestionCollection();

                String customQuestion = questionfield.getText();
                String customCorrectAnswer = correctAnswer.getText();
                String customIncorrectAnswer1 = incorrectAnswer1.getText();
                String customIncorrectAnswer2 = incorrectAnswer2.getText();
                String customIncorrectAnswer3 = incorrectAnswer3.getText();

                List<String> customIncorrectAnswerList = new ArrayList<>();
                customIncorrectAnswerList.add(customIncorrectAnswer1);
                customIncorrectAnswerList.add(customIncorrectAnswer2);
                customIncorrectAnswerList.add(customIncorrectAnswer3);

                Document newCustomQuestion = new Document();
                newCustomQuestion.append("question", customQuestion);
                newCustomQuestion.append("correct_answer", customCorrectAnswer);
                newCustomQuestion.append("incorrect_answers", customIncorrectAnswerList);

                //Inserting the document into the collection
                custom_question_collection.insertOne(newCustomQuestion);
            }*/
        }

        @FXML
        void cancelAndDelete(ActionEvent event) throws IOException {

            //delete fragenpackage!



            //return back to menue
          loadMenu();
        }

        @FXML
        void createPackage(ActionEvent event) throws IOException {

            //create Package

            //go back to menue
           loadMenu();
        }


        public void loadMenu() throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Quiz_Menue.fxml"));

            Stage two = new Stage();
            two.setTitle("Menue");
            two.setScene(new Scene(root));
            two.show();

            Stage stage = (Stage) cancel.getScene().getWindow();
            stage.close();
        }


    }


