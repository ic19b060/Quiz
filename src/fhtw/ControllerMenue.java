package fhtw;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static fhtw.APIReader.jsonComplete;

public class ControllerMenue implements Initializable {

    private static ControllerGameQuiz controllerGamequiz;

    @FXML
    ControllerGameQuiz controllerGameQuiz;

    private Question currentquestion;

    //Welcome Tab
    @FXML
    public Tab startTab;

    @FXML
    private Label welcomLbl;

    @FXML
    private Button gotoprofileBtn;

    @FXML
    private Button highscoreBtn;

    @FXML
    private Label explanationLbl;

    //SingleplayerTab
    @FXML
    private Tab spTab;

    @FXML
    private Spinner<Integer> spinnerQuestionNumber;

    @FXML
    private Label nbrLbl;

    @FXML
    private Label diffLbl;

    @FXML
    private Label catLbl;

    @FXML
    private Button startBtnSp;

    @FXML
    private ComboBox<String> comboCat;

    @FXML
    private ComboBox<String> comboDiff;

    //CustomGameTab
    @FXML
    public Tab customGameTab;

    @FXML
    private Button startCustomGameBtn;

    @FXML
    private ComboBox<String> QuestionCollectionCombo;

    @FXML
    private Button createQuestion;

    //Profil tab
    @FXML
    private Tab profilTab;

    @FXML
    private TextArea nameFieldProf;


    @FXML
    private TextArea highscoreFieldProf;

    //Highscore Tab
    @FXML
    public Tab highscoreTab;

    @FXML
    private TextArea txtAreaHighscore;

    //Logout Tab
    @FXML
    private Tab logoutTab;

    @FXML
    private Button lgoutBtn;

    @FXML
    void createQuestion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createCustomizedQuestions.fxml"));

        Stage three = new Stage();
        three.setTitle("Creating mode");
        three.setScene(new Scene(root));
        three.show();

        Stage stage = (Stage) createQuestion.getScene().getWindow();
        stage.close();
    }

    public ControllerMenue() {

    }

    public void logout(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));

        Stage three = new Stage();
        three.setTitle("Quiz");
        three.setScene(new Scene(root));
        three.show();

        Stage stage = (Stage) lgoutBtn.getScene().getWindow();
        stage.close();
    }


    public void gotoProfile(ActionEvent actionEvent) {
        startTab.getTabPane().getSelectionModel().select(profilTab);
    }

    public void gotoHighscores(ActionEvent actionEvent) {
        startTab.getTabPane().getSelectionModel().select(highscoreTab);
    }


    @FXML
    void startCustomGame(ActionEvent event) throws IOException {

        //get document name from dropdown menu
        String name = QuestionCollectionCombo.getSelectionModel().getSelectedItem();

        Parent root = FXMLLoader.load(getClass().getResource("gameQuiz.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection<Document> game_collection = db.getCollection("CustomGame");

            MongoCursor<Document> cursor = game_collection.find().iterator();

            String dbname = "";
            Document game = new Document();

            while (cursor.hasNext()) {
                 game = cursor.next();
                dbname = game.getString("name");

                if (dbname.equals(name)) {
                    break;
                }
            }

            Gson g = new Gson();

            System.out.println(game.toString());
            System.out.println(game.toJson());

            //g.fromJson(game.toJson(), ArrayList.class).forEach(q -> System.out.println(q.toString()));

            //List < Question > questions = ParseQuestionstoJson.parseQuestionJson(game.toJson());




            //Question q = g.fromJson(String.valueOf(game), Question.class);
          //  JsonParser parser = new JsonParser();
           // JsonObject json = (JsonObject) parser.parse(game.toJson());

            //List<Question> questions = ParseQuestionstoJson.parseQuestionJson(json);


            //QuestionRepository.getInstance().setQuestions(questions);
            controllerGameQuiz.setNextQuestion();

        }



        Stage stage = (Stage) startCustomGameBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    void startSPQuiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameQuiz.fxml"));
        Parent root = loader.load();

        //loader.setController(this);
        this.controllerGameQuiz = loader.getController(); // controller aus dem Loader bekommen
        this.controllerGameQuiz.setController1(this);

        String link = get_values().getApiPath();
        //create question set with created link for API
        JsonObject questionsjson = jsonComplete(link);

        //Gameplay logic

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        //Gameplaylogic

        List<Question> questions = ParseQuestionstoJson.parseQuestionJson(questionsjson);

        QuestionRepository.getInstance().setQuestions(questions);

        controllerGameQuiz.setNextQuestion();


        //socket öffnen für den chat??

        Stage stage = (Stage) startBtnSp.getScene().getWindow();
        stage.close();
    }


    @FXML
    public void loadDataDiffbutton(ComboBox<String> name) {
        ObservableList<String> difficultylist;
        difficultylist = FXCollections.observableArrayList();
        difficultylist.addAll("easy", "medium", "hard");
        name.getItems().addAll(difficultylist);
    }

    @FXML
    public void loadDataCatbutton(ComboBox<String> name) {
        ObservableList<String> categorylist;
        categorylist = FXCollections.observableArrayList();
        categorylist.addAll("General_Knowledge", "Books", "Film", "Music",
                "Musical_and_Theatre", "Television", "VideoGames", "BoardGames", "Science_and_Nature",
                "Computers", "Mathematics", "Mythology", "Sports", "Geography", "History", "Politics", "Art", "Celebreties", "Animals", "Vehicles",
                "Comics", "Gadgets", "Japanese_Anime_Manga", "Cartoon_and_Animation");

        name.getItems().addAll(categorylist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataDiffbutton(comboDiff);
        loadDataCatbutton(comboCat);

        //TODO - Daten von DB holen für Dropdownmenü in customgame.
        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection<Document> user_collection = db.getCollection("CustomGame");

            MongoCursor<Document> cursor = user_collection.find().iterator();

            ArrayList<String> gameNames = new ArrayList<>();

            while (cursor.hasNext()) {
                Document gameinfo = cursor.next();
                gameNames.add(gameinfo.getString("name"));
            }

            QuestionCollectionCombo.getItems().addAll(gameNames);



        }
    }


    public static List<String> shuffleAnswers(Question question) {

        List<String> rand_answers = new ArrayList<>(question.getIncorrectAnswers());
        rand_answers.add(question.getCorrectAnswer());

        Collections.shuffle(rand_answers);
        return rand_answers;
    }


    public QuestionProvider get_values() {

        //int value = nmb_dropdwn.getValue();
        //System.out.println(value);
        //System.out.println(nmb_dropdwn.getValue());

        return new QuestionProvider(spinnerQuestionNumber.getValue(), comboDiff.getSelectionModel().getSelectedItem(), comboCat.getSelectionModel().getSelectedItem(), "multiple");
    }

}


