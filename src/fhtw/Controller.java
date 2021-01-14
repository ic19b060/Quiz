package fhtw;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Controller  {

    ArrayList<mainuserdatabase> userdatabase = new ArrayList<>();


    ObservableList<String> categorylist = FXCollections.observableArrayList();


    @FXML
    private Tab start_tab;

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
    private Spinner<?> nmb_dropdwn;

    @FXML
    private Label nbr_lbl;

    @FXML
    private Label diff_lbl;

    @FXML
    private Label cat_lbl;

    @FXML
    private Button start_btn_sp;

    @FXML
    private Spinner<?> diff_drpdwn;

    @FXML
    private Spinner<?> cat_drpdwn;

    @FXML
    private Tab mp_tab;

    @FXML
    private Button start_btn_mp;

    @FXML
    private Spinner<?> nbr_drpdwn_mp;



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
    private Tab highscore_tab;

    @FXML
    private TextArea txt_area_highscore;

    @FXML
    private Tab logout_tab;

    @FXML
    private Button lgout_btn;


    @FXML
    private PasswordField psw_field;

    @FXML
    private Button lgn_btn;


    @FXML
    private Button sign_up_btn;

    @FXML
    private Label lbl_usr;

    @FXML
    private Label lbl_pwd;

    @FXML
    private Label welcome_txt;

    @FXML
    private TextField user_field;

    @FXML
    private Label lbl_usr1;

    @FXML
    private Button button_a;

    @FXML
    private Button button_d;

    @FXML
    private Button button_b;

    @FXML
    private Button button_c;

    @FXML
    private TextArea text_fragen;

    @FXML
    private Button button_Quizgamequiz;

    @FXML
    private Button button_joker;

    @FXML
    private TextArea chat_fenster;

    @FXML
    private TextField chat_textfenster;

    @FXML
    private Button send_button_chat;

    @FXML
    private Label lbl_loginstatus;

    @FXML
    private TextField enter_user_signup;

    @FXML
    private PasswordField enter_password_signup;

    @FXML
    private Button ok_signup_btn;

    @FXML
    void quitgamequiz(ActionEvent event) throws IOException {
       Stage stage = (Stage) button_Quizgamequiz.getScene().getWindow();
        stage.close();


        //highscores speichern in files
        //aktualisieren im Profil

    }

    @FXML
    public void initialize() {


        //???
        //funktioniert noch nicht



        //arraylist einfügen für login/registrierung
    }

    public Controller() {

    }


    public void buttononAction_Login(ActionEvent actionEvent) throws IOException {

        String user = user_field.getText();
        String pwd = psw_field.getText();

       // if (userdatabase.contains(user) && userdatabase.contains(pwd)) {


            Parent root = FXMLLoader.load(getClass().getResource("Quiz_Menue.fxml"));

            Stage two = new Stage();
            two.setTitle("Quiz");
            two.setScene(new Scene(root));
            two.show();

            Stage stage = (Stage) lgn_btn.getScene().getWindow();
            stage.close();

      //  } else {
      //      lbl_loginstatus.setText("no such user - please sign up!");
      //  }

        //beim login passwort checken.
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
        Parent root = FXMLLoader.load(getClass().getResource("Game_quiz.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        //socket öffnen für den chat??


    }
    @FXML
    public void load_data_catbutton(ContextMenuEvent mouseEvent) {
        ObservableList<String> difficultylist;

        difficultylist = FXCollections.observableArrayList();
        String a = "easy";
        String b = "medium";
        String c = "hard";
        difficultylist.addAll(a, b, c);
        dif_drp_mp.getItems().addAll(difficultylist);
    }



    public void signup_newuserentry(ActionEvent actionEvent) {

        //funktioniert noch nicht.. datenbank wird nach schließen der gui gelöscht.



        String user = enter_user_signup.getText();
        String pwd = enter_password_signup.getText();

        mainuserdatabase u = new mainuserdatabase(user,pwd);

        userdatabase.add(u);

        enter_password_signup.clear();
        enter_user_signup.clear();
        for(mainuserdatabase p : userdatabase){
            enter_user_signup.appendText(p.toString() +"\n");
            enter_password_signup.appendText(p.toString() + "\n");


        }

        for(mainuserdatabase p : userdatabase){
            System.out.println(p);

        }



        //entry in list speichern
        Stage stage = (Stage) ok_signup_btn.getScene().getWindow();
        stage.close();
    }

    public void opensignup_btn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sign_up.fxml"));

        Stage two = new Stage();
        two.setTitle("Sign-up");
        two.setScene(new Scene(root));
        two.show();
    }


    class mainuserdatabase{

        String user;
        String password;

        public mainuserdatabase(String user, String password) {
            this.user = user;
            this.password = password;
        }

        @Override
        public String toString() {
            return "userdatabase{" +
                    "user='" + user + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }
}


