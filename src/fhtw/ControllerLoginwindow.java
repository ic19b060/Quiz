package fhtw;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
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

        Stage stage = (Stage) sign_up_btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void buttononAction_Login(ActionEvent event) throws IOException {

        String user = user_field.getText();
        String pwd = psw_field.getText();

        //connect to the database and retrieve the User profiles
        MongoDatabase database = MongoDB.connect_to_db();
        MongoCollection<Document> user_collection = database.getCollection("Users");

        //check if the username exists in the database
        //BasicDBObject query = new BasicDBObject();
        //query.put("Username", user);
        //FindIterable<Document> cur_user = user_collection.find(query);

        //check if username is in database
        MongoCursor<Document> cursor = user_collection.find().iterator();
        String usernameDB = "";
        String passwordDB = "";

            while (cursor.hasNext()) {
                Document userinfo = cursor.next();
                usernameDB = userinfo.getString("Username");
                passwordDB = userinfo.getString("Password");
                if (usernameDB.equals(user)) {
                break;
                }
            }

        if (!usernameDB.equals(user)) {
            lbl_loginstatus.setText("no such user - please sign up!");
            System.out.println(usernameDB + "-usernameDB, user-" + user);

        } else {
            //check if the password is correct
            BasicDBObject criteria = new BasicDBObject();
            criteria.append("Username", user);
            criteria.append("Password", pwd);
            FindIterable<Document> cur_profile = user_collection.find(criteria);

            if (!passwordDB.equals(pwd)) {
                lbl_loginstatus.setText("Wrong password! Please try again or contact the developer team");
            } else {
                //all checks passed! Ready for login
                Parent root = FXMLLoader.load(getClass().getResource("Quiz_Menue.fxml"));

                Stage two = new Stage();
                two.setTitle("Quiz");
                two.setScene(new Scene(root));
                two.show();

                Stage stage = (Stage) lgn_btn.getScene().getWindow();
                stage.close();

            }
        }

    }
}


