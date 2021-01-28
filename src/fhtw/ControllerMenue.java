package fhtw;

import com.google.gson.*;
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
import java.util.*;
import java.util.stream.IntStream;

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
    private Label proflblname;

    @FXML
    private Label proflbltempscore;

    @FXML
    private Label proflblhighscore;

    @FXML
    private Label proflbljoker;



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

    /**
     * If Modus Custom Game is chosen, a new Window for creating personalized Questions is opened.
     *
     * @param event
     * @throws IOException
     */
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

    /**
     * By clicking logout, the Loginwindow will be opened again.
     * @param actionEvent
     * @throws IOException
     */
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

    /**
     * To start the custom Gamequiz with Questions from the database
     * We connect to the database and load the collection "CustomGame"
     * We look for the Document with the correct name chosen by the player
     * This Document we get as JsonObject and parse with our own parser function into a question list
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void startCustomGame(ActionEvent event) throws IOException {

        //get document name from dropdown menu
        String name = QuestionCollectionCombo.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameQuiz.fxml"));
        Parent root = loader.load();

        this.controllerGameQuiz = loader.getController();
        this.controllerGameQuiz.setController1(this);

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
            String DBjson = game.toJson();

            JsonObject jsonObject = null;

            try {
                jsonObject = JsonParser.parseString(DBjson).getAsJsonObject();
                JsonArray gamearr = (JsonArray) jsonObject.get("questions");

                for (Object objInArr : gamearr) {

                    JsonObject jsonquestion = (JsonObject) objInArr;
                    List<JsonElement> answers = new ArrayList<>();
                    System.out.println("correct: " + jsonquestion.get("correctAnswer"));
                    answers.add(jsonquestion.get("correctAnswer"));
                    answers.add(jsonquestion.get("incorrectAnswers"));
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }

            JsonObject questionsjson = jsonObject;

            Stage two = new Stage();
            two.setTitle("Quiz");
            two.setScene(new Scene(root));
            two.show();

            List<Question> questions = ParseQuestionsJson.parseCustomQuestionJson(questionsjson);
            QuestionRepository.getInstance().setQuestions(questions);
            controllerGameQuiz.setNextQuestion();
        }

        Stage stage = (Stage) startCustomGameBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * To start the normal Gamequiz with Questions from API.
     * Method call for API_Link, parsing Questions and set the first question into the new fxml Interface File gameQuiz.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void startSPQuiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("gameQuiz.fxml"));
        Parent root = loader.load();


        this.controllerGameQuiz = loader.getController();
        this.controllerGameQuiz.setController1(this);

        String link = get_values().getApiPath();
        //create question set with created link for API
        JsonObject questionsjson = jsonComplete(link);


        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();


        List<Question> questions = ParseQuestionsJson.parseQuestionJson(questionsjson);
        QuestionRepository.getInstance().setQuestions(questions);
        controllerGameQuiz.setNextQuestion();


        Stage stage = (Stage) startBtnSp.getScene().getWindow();
        stage.close();
    }

/**
 * Fills in the Combobox with data.
 */
    @FXML
    public void loadDataDiffbutton(ComboBox<String> name) {
        ObservableList<String> difficultylist;
        difficultylist = FXCollections.observableArrayList();
        difficultylist.addAll("easy", "medium", "hard");
        name.getItems().addAll(difficultylist);
    }

    /**
     * Fills in the Combobox with data.
     */
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

    /**
     * Method is "called" before opening the fxml.
     * Initializing Combox_boxes in Normal Game & Custom Game.
     * Has to connect to MongoDB for loading all customized Documents.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataDiffbutton(comboDiff);
        loadDataCatbutton(comboCat);
        String username = PersonalData.getInstance().getUsername();
        Integer highscore = PersonalData.getInstance().getHighscore();
        Integer curHighscore = PersonalData.getInstance().getTempScore();
        Integer joker = PersonalData.getInstance().getJoker();

        //TODO Textfield für joker + gesamthighscore
        //Show in Profile:
        proflblname.setText(username);
        proflbljoker.setText(joker.toString());
        proflblhighscore.setText(String.valueOf(highscore));
        proflbltempscore.setText(String.valueOf(curHighscore));
        //highscoreFieldProf.setText(String.valueOf(curHighscore));


        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection<Document> user_collection = db.getCollection("CustomGame");
            MongoCollection<Document> highscore_collection = db.getCollection("Users");

            MongoCursor<Document> gameCursor = user_collection.find().iterator();
            MongoCursor<Document> highscoreCursor = highscore_collection.find().iterator();

            ArrayList<String> gameNames = new ArrayList<>();
            List<Highscore> highscoreList = new ArrayList<>();
            ArrayList<String> stringList = new ArrayList<>();


            while (gameCursor.hasNext()) {
                Document gameinfo = gameCursor.next();
                gameNames.add(gameinfo.getString("name"));
            }

            while (highscoreCursor.hasNext()) {
                Document user = highscoreCursor.next();
                Highscore h = new Highscore();
                h.setHighscoreFromDB(user.getInteger("Highscore"));
                h.setUsernameHighscoreFromDB(user.getString("Username"));
                //txtAreaHighscore.(h.getUsernameHighscoreFromDB());
                txtAreaHighscore.setText(String.valueOf((h.getUsernameHighscoreFromDB())) + String.valueOf((h.getHighscoreFromDB())));
                //txtAreaHighscore.setText()
                //txtLog.getText() + "\n" + s
            }

            StringBuilder b = new StringBuilder();
            highscoreList.forEach(b::append);

            // Program to convert Object array to String array in Java
//new textarea
                    QuestionCollectionCombo.getItems().addAll(gameNames);
        }
    }

    /**
     * Shuffles the answers for the GameQuiz.
     * @param question
     * @return
     */
    public static List<String> shuffleAnswers(Question question) {

        List<String> rand_answers = new ArrayList<>(question.getIncorrectAnswers());
        rand_answers.add(question.getCorrectAnswer());

        Collections.shuffle(rand_answers);
        return rand_answers;
    }

    /**
     * reads the values from ComboBoxes and Spinner to create the API Link.
     * @return
     */
    public QuestionProvider get_values() {

        return new QuestionProvider(spinnerQuestionNumber.getValue(), comboDiff.getSelectionModel().getSelectedItem(), comboCat.getSelectionModel().getSelectedItem(), "multiple");
    }

}


