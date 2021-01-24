
package fhtw;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class TradeCustomCollectionController {

    @FXML
    private Button forward_button;

    @FXML
    private TextField enterCollectionName;

    @FXML
    void gotonextWindow(ActionEvent event) throws IOException {
        CustomQuestions.createCustomQuestionCollection(getCollectionName());
        Parent root = FXMLLoader.load(getClass().getResource("createQuestions.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) forward_button.getScene().getWindow();
        stage.close();
    }


     public String getCollectionName(){
         String customCollectionName = enterCollectionName.getText();
         return customCollectionName;

     }

}
