
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

public class ControllergetCollectionsName {

    @FXML
    private Button forwardButton;

    @FXML
    private TextField enterCollectionName;

    @FXML
    void gotonextWindow(ActionEvent event) throws IOException {
        CustomQuestions.createCustomQuestionCollection(getCollectionName());
        Parent root = FXMLLoader.load(getClass().getResource("createCustomizedQuestions.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) forwardButton.getScene().getWindow();
        stage.close();
    }


     public String getCollectionName(){
         return enterCollectionName.getText();

     }

}
