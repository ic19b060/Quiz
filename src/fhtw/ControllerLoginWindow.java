package fhtw;

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



public class ControllerLoginWindow implements Initializable {


    PersonalData personaldata = new PersonalData();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private PasswordField passwordField;

    @FXML
    private Button lgnBtn;

    @FXML
    private Button signUpBtn;

    @FXML
    private Label lblUsr;

    @FXML
    private Label lblPwd;

    @FXML
    private Label welcomeTxt;

    @FXML
    private TextField userField;

    @FXML
    private Label lblUsr1;

    @FXML
    private Label lblLoginstatus;


    /**
     * Loads the ned window for signing up a new User.
     *
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void opensignupBtn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signUp.fxml"));

        Stage two = new Stage();
        two.setTitle("Sign-up");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) signUpBtn.getScene().getWindow();
        stage.close();
    }

    /**
     *
     * By clicking on Login, we get the userinput, Connect to MongoDB and compare User-input with the Database.
     * The Labels will be set after checking with the Database if User is valid or not.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void buttononActionLogin(ActionEvent event) throws IOException {

        String user = userField.getText();
        String pwd = passwordField.getText();

        //connect to the database and retrieve the User profiles
        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection<Document> user_collection = db.getCollection("Users");

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
                lblLoginstatus.setText("No such user - please sign up!");
            } else {
                if (!passwordDB.equals(pwd)) {
                    lblLoginstatus.setText("Wrong password! Please try again or contact the developer team");
                } else {

                    PersonalData.getInstance().setUsername(user);

                    Parent root = FXMLLoader.load(getClass().getResource("menue.fxml"));

                    Stage two = new Stage();
                    two.setTitle("Menue");
                    two.setScene(new Scene(root));
                    two.show();

                    Stage stage = (Stage) lgnBtn.getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
}


