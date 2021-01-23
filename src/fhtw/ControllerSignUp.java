package fhtw;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.bson.Document;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerSignUp implements Initializable {

    //ArrayList<mainuserdatabase> userdatabase = new ArrayList<>();
    @FXML
    private TextField enter_user_signup;

    @FXML
    private PasswordField enter_password_signup;

    @FXML
    private Button ok_signup_btn;


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

        if (cur_user != null) {
            //xy.setText("Error: Username already exists");

        } else {
                //Insert document for new user into database
                Document newUser = new Document();
                newUser.append("Username", user);
                newUser.append("Password", pwd);
                user_collection.insertOne(newUser);
                //print "Sign-Up successfull! Welcome " + user + "!"
            }

            /*userdatabase.add(u);

        enter_password_signup.clear();
        enter_user_signup.clear();
        for (mainuserdatabase p : userdatabase) {
            enter_user_signup.appendText(p.toString() + "\n");
            enter_password_signup.appendText(p.toString() + "\n");


        }

        for (mainuserdatabase p : userdatabase) {
            System.out.println(p);

        }


        //entry in list speichern
        Stage stage = (Stage) ok_signup_btn.getScene().getWindow();
        stage.close();
    }


    static class mainuserdatabase {

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
    }*/

    }

}