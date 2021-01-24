package fhtw;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
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
import org.bson.Document;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerSignUp implements Initializable {


    @FXML
    private Label signuplbl;

    @FXML
    private TextField enterUserSignup;

    @FXML
    private PasswordField enterPasswordSignup;

    @FXML
    private Button okSignupBtn;

    @FXML
    private Button rtrnLogin;

    @FXML
    void returnLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("loginWindow.fxml"));

        Stage two = new Stage();
        two.setTitle("Login");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) rtrnLogin.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void signupNewuserentry(ActionEvent actionEvent) {

        String user = enterUserSignup.getText();
        String pwd = enterPasswordSignup.getText();

        //connect to the database and retrieve the User profiles
        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection<Document> user_collection = db.getCollection("Users");

            //check if the username exists in the database
            BasicDBObject query = new BasicDBObject();
            query.put("Username", user);
            FindIterable<Document> curUser = user_collection.find(query);

            //check if username is in database
            MongoCursor<Document> cursor = user_collection.find().iterator();
            String usernameDB = "";

            while (cursor.hasNext()) {
                Document userinfo = cursor.next();
                usernameDB = userinfo.getString("Username");

                if (usernameDB.equals(user)) {
                    signuplbl.setText("Error: Username already exists");
                } else if ((!usernameDB.equals(user)) && (!cursor.hasNext())) {
                    //Insert document for new user into database
                    Document newUser = new Document();
                    newUser.append("Username", user);
                    newUser.append("Password", pwd);
                    user_collection.insertOne(newUser);
                    signuplbl.setText("Sign-Up successful! \n Welcome " + user + "!");
                    okSignupBtn.setDisable(true);

                }

            }

        }

    }
}