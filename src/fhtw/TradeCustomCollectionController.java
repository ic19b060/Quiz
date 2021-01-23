
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

public class TradeCustomCollectionController {

    @FXML
    private Button forward_button;

    @FXML
    private TextField enterCollectionName;

    @FXML
    void gotonextWindow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("createQuestions.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) forward_button.getScene().getWindow();
        stage.close();
    }

}
