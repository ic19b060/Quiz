package fhtw;

import com.google.gson.JsonObject;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import static fhtw.APIReader.Json_complete;

public class Controller implements Initializable {

    private static GamequizController controllerGamequiz;

    @FXML
    GamequizController gamequizController;



    private Question currentquestion;



    ObservableList<String> categorylist = FXCollections.observableArrayList();


    @FXML
    public Tab start_tab;

    @FXML
    private Label Welcom_lbl;

    @FXML
    private Button gotoprofile_btn;

    @FXML
    private Button highscore_btn;

    @FXML
    private Label explanation_lbl;

    @FXML
    private Tab sp_tab;

    @FXML
    private Spinner<Integer> nmb_dropdwn;

    @FXML
    private Label nbr_lbl;

    @FXML
    private Label diff_lbl;

    @FXML
    private Label cat_lbl;

    @FXML
    private Button start_btn_sp;

    @FXML
    public ChoiceBox<String> cat_drp_sp;

    @FXML
    public ChoiceBox<String> diff_drpdwn_sp;


    @FXML
    private Tab mp_tab;

    @FXML
    private Button start_btn_mp;

    @FXML
    private static Spinner<Integer> nbr_drpdwn_mp;



    @FXML
    private ChoiceBox<String> cat_drp_mp;


    @FXML
    public ChoiceBox<String> dif_drp_mp;

    @FXML
    private Tab profil_tab;

    @FXML
    private TextArea name_field_prof;

    @FXML
    private TextArea login_field_prof;


    @FXML
    private TextArea highscore_field_prof;


    @FXML
    public Tab highscore_tab;

    @FXML
    private TextArea txt_area_highscore;

    @FXML
    private Tab logout_tab;

    @FXML
    private Button lgout_btn;


    @FXML
    void quitgamequiz(ActionEvent event) throws IOException {
       Stage stage = (Stage) fhtw.Controller.controllerGamequiz.button_Quizgamequiz.getScene().getWindow();
        stage.close();


        //highscores speichern in files
        //aktualisieren im Profil

    }



    public Controller() {

    }




    public void logout(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Stage three = new Stage();
        three.setTitle("Quiz");
        three.setScene(new Scene(root));
        three.show();

        Stage stage = (Stage) lgout_btn.getScene().getWindow();
        stage.close();
    }


    public void gotoProfile(ActionEvent actionEvent) {
        start_tab.getTabPane().getSelectionModel().select(profil_tab);
    }

    public void gotoHighscores(ActionEvent actionEvent) {
        start_tab.getTabPane().getSelectionModel().select(highscore_tab);
    }


    @FXML
    void startmultiplayerquiz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Game_quiz.fxml"));


        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();
    }

    @FXML
    void startquiz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game_quiz.fxml"));
        Parent root = (Parent) loader.load();

        //loader.setController(this);
        this.gamequizController = loader.getController(); // controller aus dem Loader bekommen
        this.gamequizController.setController1(this);

        String link = fhtw.Link.Link();

       // String link = get_values().getApiPath();


        //create question set with created link for API
        JsonObject questionsjson = Json_complete(link);

        //Gameplay logic


        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        //Gameplaylogic


        List<Question> questions = Answers.parseQuestionJson(questionsjson);

        QuestionRepository.getInstance().setQuestions(questions);


        gamequizController.setNextQuestion();


        //Felder einlesen für schwierigkeit,usw
        //Quiz starten /api laden?
        //Fragen in Textfeld schreiben
        //Antworten mit button verknüpfen.

       //text_fragen.setText("Hallo");
        //socket öffnen für den chat??


    }




    @FXML
    public void loadDataDiffbutton(ChoiceBox<String> name){
        ObservableList<String> difficultylist;
        difficultylist = FXCollections.observableArrayList();
        difficultylist.addAll("easy", "medium", "hard");
        name.getItems().addAll(difficultylist);
            }


    @FXML
    public void loadDataCatbutton(ChoiceBox<String> name){
        ObservableList<String> categorylist;
        categorylist = FXCollections.observableArrayList();
        categorylist.addAll("General Knowledge", "Books", "Film","Music",
                "Musical & Theatres","Television","Video Games","Board Games","Science & Nature",
                "Computers","Mathematics","Mythology","Sports","Geography","History","Politics");

        name.getItems().addAll(categorylist);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDataDiffbutton(diff_drpdwn_sp);
        loadDataDiffbutton(dif_drp_mp);
        loadDataCatbutton(cat_drp_mp);
        loadDataCatbutton(cat_drp_sp);
    }


    public static List<String> shuffleAnswers(Question question) {

        List<String>  rand_answers = new ArrayList<>();
        rand_answers.addAll(question.getIncorrect_answers());
        rand_answers.add(question.getCorrect_answer());

        Collections.shuffle(rand_answers);
        return rand_answers;
    }


    public QuestionProvider get_values() {

        //int value = nmb_dropdwn.getValue();
        //System.out.println(value);
        //System.out.println(nmb_dropdwn.getValue());


        QuestionProvider questionProvider = new QuestionProvider(nmb_dropdwn.getValue(),diff_drpdwn_sp.getSelectionModel().getSelectedItem(),cat_drp_sp.getSelectionModel().getSelectedItem(),"&type=multiple" );




        return questionProvider;
    }

}


