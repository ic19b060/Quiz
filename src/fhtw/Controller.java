package fhtw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;

public class Controller {

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
    private Spinner<?> diff_drpdwn_mp;

    @FXML
    private Button start_btn_mp;

    @FXML
    private Spinner<?> cat_drpdwn_mp;

    @FXML
    private Spinner<?> nbr_drpdwn_mp;

    @FXML
    private Tab profil_tab;

    @FXML
    private TextArea name_field_prof;

    @FXML
    private TextArea login_field_prof;

    @FXML
    private TextArea highscore_field_prof;

    @FXML
    private Tab bestenliste_tab;

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

    public Controller() {
    }


    public void buttononAction_Login(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Quiz_Menue.fxml"));


        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) lgn_btn.getScene().getWindow();
        stage.close();
    }



     public void logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) lgout_btn.getScene().getWindow();
       stage.close();
    }


}

