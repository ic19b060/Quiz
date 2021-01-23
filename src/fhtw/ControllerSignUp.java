package fhtw;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerSignUp implements Initializable {


    @FXML
    private Label signuplbl;

    @FXML
    private TextField enter_user_signup;

    @FXML
    private PasswordField enter_password_signup;

    @FXML
    private Button ok_signup_btn;

    @FXML
    private Button rtrnLogin;

    @FXML
    void returnLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

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

    public void signup_newuserentry(ActionEvent actionEvent) {

        String user = enter_user_signup.getText();
        String pwd = enter_password_signup.getText();

        //connect to the database and retrieve the User profiles
        MongoDatabase database = MongoDB.connect_to_db();
        MongoCollection<Document> user_collection = database.getCollection("Users");

        //check if the username exists in the database
        BasicDBObject query = new BasicDBObject();
        query.put("Username", user);
        FindIterable<Document> cur_user = user_collection.find(query);

        //check if username is in database
        MongoCursor<Document> cursor = user_collection.find().iterator();
        String usernameDB = "";

        while (cursor.hasNext()) {
            Document userinfo = cursor.next();
            usernameDB = userinfo.getString("Username");

            if (usernameDB.equals(user)) {
                signuplbl.setText("Error: Username already exists");
            }
            else if ((!usernameDB.equals(user)) && (!cursor.hasNext())){
                //Insert document for new user into database
                Document newUser = new Document();
                newUser.append("Username", user);
                newUser.append("Password", pwd);
                user_collection.insertOne(newUser);
                signuplbl.setText("Sign-Up successful! \n Welcome " + user + "!");
                ok_signup_btn.setDisable(true);
              
            }

        }

    }

}