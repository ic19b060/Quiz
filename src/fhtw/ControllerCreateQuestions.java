package fhtw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
        private TextField inCorrectAnswer2;

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


            //Fragen zum Package hinzufügen

            //Bspcode fürs einlesen der textfelder!
            //correctAnswer.getText();
            //incorrectAnswer1.getText();
           // inCorrectAnswer2.getText();
           // incorrectAnswer3.getText();

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


