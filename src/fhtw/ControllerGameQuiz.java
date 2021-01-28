package fhtw;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.bson.Document;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the Game_Quiz
 */
public class ControllerGameQuiz implements Initializable {

    OutputStream out;
    Socket client;
    Integer tempscore = 0;
    private Question currentquestion;

    @FXML
    ControllerMenue ControllerMenue;

    public void setController1(ControllerMenue ControllerMenue) {

        this.ControllerMenue = ControllerMenue;
    }

    private int JokerCounter;

    @FXML
    public Button buttonA;

    @FXML
    public Button buttonD;

    @FXML
    public Button buttonB;

    @FXML
    public Button buttonC;

    @FXML
    public TextArea textFragen;

    @FXML
    public Button buttonQuizgamequiz;

    @FXML
    private Button buttonJoker;

    @FXML
    private TextArea chatFenster;

    @FXML
    private TextField chatTextfenster;

    @FXML
    private Button sendButtonChat;

    @FXML
    private Label wrongAnswerLBL;

    @FXML
    private Label rightAnswerlbl;

    @FXML
    private Button nextQuestion;


    @FXML
    private ImageView imageView;

    @FXML
    private Label randomJokerlbl;


    /**
     * If Button next Question is clicked - all Buttons are resetted and enabled again.
     * Method call to set the next Question
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void nextQuestion(ActionEvent event) throws IOException {
        wrongAnswerLBL.setText("");
        rightAnswerlbl.setText("");

        buttonA.setDisable(false);
        buttonB.setDisable(false);
        buttonC.setDisable(false);
        buttonD.setDisable(false);

        buttonJoker.setDisable(false);
        setNextQuestion();
        JokerCounter = 0;
    }

    /**
     * If Joker is clicked, 2 answers are deleted and the Joker Button will be disabled.
     *
     * @param event
     */
    @FXML
    void JokerClicked(ActionEvent event) {
        if (PersonalData.getInstance().getJoker() > 0) {
            if (JokerCounter == 0) {
                if (currentquestion.getCorrectAnswer().equals((buttonA.getText()))) {
                    buttonB.setText("");
                    buttonC.setText("");
                } else if (currentquestion.getCorrectAnswer().equals((buttonB.getText()))) {
                    buttonA.setText("");
                    buttonD.setText("");
                } else if (currentquestion.getCorrectAnswer().equals((buttonC.getText()))) {
                    buttonD.setText("");
                    buttonA.setText("");
                } else if (currentquestion.getCorrectAnswer().equals((buttonD.getText()))) {
                    buttonB.setText("");
                    buttonA.setText("");
                }
                JokerCounter++;
                PersonalData.getInstance().setJoker(PersonalData.getInstance().getJoker() - 1);
                buttonJoker.setDisable(true);
            }
        }
    }



    /**
     * Checks if the Answer is correct and sets the Label.
     * @param answer
     */
    private void answer(String answer) {
        if (currentquestion.getCorrectAnswer().equals(answer)) {
            rightAnswerlbl.setText("Correct!");
            tempscore += 20;
        } else {
            String answerLabel = currentquestion.getCorrectAnswer();
            wrongAnswerLBL.setText("That was the wrong answer :( - The right answer was:");
            rightAnswerlbl.setText(answerLabel);
        }
    }

    /**
     * Gets new Question from the Repository, parse it into Question and shuffle the answers.
     * If there is no question left, the Game is over and Highscore is printed into Tab Profile and in our txt File.
     *
     * @throws IOException
     */
    public void setNextQuestion() throws IOException {
        List<Question> questions = QuestionRepository.getInstance().getQuestions();
        if (questions.isEmpty()) {
            buttonQuizgamequiz.setStyle("-fx-background-color:orangered");
            nextQuestion.setDisable(true);
            buttonJoker.setDisable(true);
            imageView.setVisible(true);

            PersonalData.getInstance().setTempScore(tempscore);

            //generate random jokers
            Integer max = 100;
            Integer min = 0;
            Double number = Math.random() * (max - min + 1) + min;
            if (number > 0){
                PersonalData.getInstance().setJoker(PersonalData.getInstance().getJoker()+1);
                randomJokerlbl.setText("Congratulations, you've won a Joker!");
            }

                try (MongoClient client = MongoDB.connectToDb()) {
                    MongoDatabase db = MongoDB.getDB(client);
                    MongoCollection userCollection = db.getCollection("Users");

                    //Updating a document
                    MongoCursor<Document> cursor = userCollection.find().iterator();
                    String usernameDB = "";
                    while (cursor.hasNext()) {
                        Document userinfo = cursor.next();
                        usernameDB = userinfo.getString("Username");

                        if (usernameDB.equals(PersonalData.getInstance().getUsername())) {
                            userCollection.updateOne(Filters.eq("Username", PersonalData.getInstance().getUsername()), Updates.set("TempScore", PersonalData.getInstance().getTempScore()));
                            if(tempscore >= PersonalData.getInstance().getHighscore() || PersonalData.getInstance().getHighscore() == null) {
                                PersonalData.getInstance().setHighscore(tempscore);
                            userCollection.updateOne(Filters.eq("Username", PersonalData.getInstance().getUsername()), Updates.set("Highscore", PersonalData.getInstance().getHighscore()));
                            if (!PersonalData.getInstance().getJoker().equals(userinfo.getInteger("Joker"))){
                                userCollection.updateOne(Filters.eq("Username", PersonalData.getInstance().getUsername()), Updates.set("Joker", PersonalData.getInstance().getJoker()));
                            }
                        }

                    }
                }
            }
            PersonalData.getInstance().writerdatainFile();

            //TODO Highscores speichern in db?
        } else {
            currentquestion = questions.get(0);
            questions.remove(0);
            List<String> answers = fhtw.ControllerMenue.shuffleAnswers(currentquestion);
            setData(answers, currentquestion.getQuestion());
        }
    }

    @FXML
    void buttonAClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonA.getText());
        disableButtons(buttonD,buttonB,buttonC);
    }

    @FXML
    void buttonBClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonB.getText());
        disableButtons(buttonA,buttonD,buttonC);
    }

    @FXML
    void buttonCClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonC.getText());
        disableButtons(buttonA,buttonB,buttonD);
    }

    @FXML
    void buttonDClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonD.getText());
        disableButtons(buttonA,buttonB,buttonC);
    }

    /**
     * By clicking at Quit, the Menue loads and the window will be closed.
     *
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void quitGameQuiz(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("menue.fxml"));

        Stage two = new Stage();
        two.setTitle("Menue");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) buttonQuizgamequiz.getScene().getWindow();
        stage.close();
    }


    public ControllerGameQuiz() {
    }

    /**
     * Sets all the data into the Buttons.
     *
     * @param answers
     * @param question
     */
    public void setData(List<String> answers, String question) {

        textFragen.setText(question);

        buttonA.setText(answers.get(0));

        buttonB.setText(answers.get(1));

        buttonC.setText(answers.get(2));

        buttonD.setText(answers.get(3));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        connecttoserver();

    }


    public void connecttoserver() {

        try {
            client = new Socket("localhost", 1111);
            System.out.println("Client connected to " + client.getInetAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public void log(String s) {
        chatFenster.appendText(s + "\n");
    }
    /**
     * Disables all the other unclicked Buttons.
     *
     * @param one Button which was not clicked
     * @param two Button which was not clicked
     * @param three Button which was not clicked
     */
    public void disableButtons(Button one, Button two, Button three){
        one.setDisable(true);
        two.setDisable(true);
        three.setDisable(true);

    }


    @FXML
    void sendButton(ActionEvent event) throws IOException {

        out = client.getOutputStream();
        String message = chatTextfenster.getText();
       byte[] bmessage = message.getBytes();
        out.write(bmessage);
        log(message);
        chatTextfenster.setText(null);

       // InputStream in = client.getInputStream();
      //  byte[] received = new byte[100]; //array mit 100 Stellen
      //  int bytes = in.read(received);
      //  String r = new String(String.valueOf(bytes));
        // log("Message from server received: " + r);


    }

}