package fhtw;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ControllerCreateCustomizedQuestions implements Initializable {

    CustomQuestionList cur;

    @FXML
    ControllerMenue controllerMenue;

    @FXML
    private TextField questionfield;

    @FXML
    private TextField correctAnswer;

    @FXML
    private TextField incorrectAnswer1;


    @FXML
    private TextField incorrectAnswer3;

    @FXML
    private Button AddQuestion;

    @FXML
    private Button CreatePackage;

    @FXML
    private Button cancel;

    @FXML
    private TextField CollectionTxtName;

    @FXML
    private Button commitName;
    @FXML
    private TextField incorrectAnswer2;

    public ControllerCreateCustomizedQuestions() {
        this.cur = new CustomQuestionList();
    }

    @FXML
    void commitNameButton(ActionEvent event) {
        questionfield.setDisable(false);
        correctAnswer.setDisable(false);
        incorrectAnswer1.setDisable(false);
        incorrectAnswer2.setDisable(false);
        incorrectAnswer3.setDisable(false);
        AddQuestion.setDisable(false);
        cancel.setDisable(false);
        CreatePackage.setDisable(false);


        CollectionTxtName.setDisable(true);
        commitName.setDisable(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        questionfield.setDisable(true);
        correctAnswer.setDisable(true);
        incorrectAnswer1.setDisable(true);
        incorrectAnswer2.setDisable(true);
        incorrectAnswer3.setDisable(true);
        AddQuestion.setDisable(true);
        cancel.setDisable(true);
        CreatePackage.setDisable(true);

    }

    @FXML
    void addQuestion(ActionEvent event) {


        String name = CollectionTxtName.getText();

        cur.setName(name); //name of the document in the database collection

        //get the user input
        Question question = new Question();

        //TODO read in document name in name-creation-interface

        //set the name of our current custom question set



        //add user input to our Question object
        question.setQuestion(questionfield.getText());
        question.setCorrectAnswer(correctAnswer.getText());
        question.setIncorrectAnswers(Arrays.asList(incorrectAnswer1.getText(), incorrectAnswer2.getText(), incorrectAnswer3.getText()));

        //add the question to our current custom question set
        cur.getQuestions().add(question);

    }


    @FXML
    void cancelAndDelete(ActionEvent event) throws IOException {

        //TODO NICI
        //delete fragenpackage!
        //return back to menue
        loadMenu();
    }

    //TODO LISL: können wir createPackage löschen?
    @FXML
    void createPackage(ActionEvent event) throws IOException {

        //connect to database and open collection "CustomGame"
        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection collections = db.getCollection("CustomGame");


            Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
            String jsonString = gson.toJson(cur);
            collections.insertOne(Document.parse(jsonString));
        }

        loadMenu();

    //create Package
    //go back to menue
    //loadMenu();
    }

    public void loadMenu() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menue.fxml"));

        Stage two = new Stage();
        two.setTitle("Menue");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }



    }