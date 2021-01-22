package fhtw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static fhtw.APIReader.Json_complete;
//import static fhtw.Link.Link;


public class ControllerLoginwindow implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




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
        private Label lbl_loginstatus;

        @FXML
        public void opensignup_btn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sign_up.fxml"));

        Stage two = new Stage();
        two.setTitle("Sign-up");
        two.setScene(new Scene(root));
        two.show();
    }

        @FXML
        public void buttononAction_Login(ActionEvent event) throws IOException {

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


    }

