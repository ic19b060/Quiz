package fhtw;


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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class ControllerGameQuiz implements Initializable {


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
    void nextQuestion(ActionEvent event) {
        wrongAnswerLBL.setText("");
        rightAnswerlbl.setText("");
        setNextQuestion();
        JokerCounter = 0;

    }

    @FXML
    void JokerClicked(ActionEvent event) {
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
            JokerCounter ++;
        }
       }
    @FXML
    void buttonAClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
       this.answer(buttonA.getText());

    }

    private void answer(String answer) {
            if (currentquestion.getCorrectAnswer().equals(answer)){
            rightAnswerlbl.setText("Correct!");
            //Highscores speichern und erhöhen
            }else{
            String answerLabel = currentquestion.getCorrectAnswer();
            wrongAnswerLBL.setText("That was the wrong answer :( - The right answer was:");
            rightAnswerlbl.setText(answerLabel);
                    }
       // setNextQuestion();

    }

    public void setNextQuestion() {
        List<Question> questions = QuestionRepository.getInstance().getQuestions();
        if (questions.isEmpty()) {
            buttonQuizgamequiz.setStyle("-fx-background-color:orangered");
            nextQuestion.setDisable(true);
            buttonJoker.setDisable(true);
            imageView.setVisible(true);


            //Highscores speichern!
        } else {
            currentquestion = questions.get(0);
            questions.remove(0);
            List<String> answers = fhtw.ControllerMenue.shuffleAnswers(currentquestion);
            setData(answers, currentquestion.getQuestion());
            }
    }
    @FXML
    void buttonBClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonB.getText());
    }

    @FXML
    void buttonCClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonC.getText());
    }

    @FXML
    void buttonDClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(buttonD.getText());
    }

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


    public void setData(List<String> answers, String question) {
        textFragen.setText(question);

        buttonA.setText(answers.get(0));

        buttonB.setText(answers.get(1));

        buttonC.setText(answers.get(2));

        buttonD.setText(answers.get(3));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}