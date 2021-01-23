package fhtw;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;


public class GamequizController {

    private Question currentquestion;
    @FXML
    Controller Controller;

    public void setController1(fhtw.Controller Controller) {
        this.Controller = Controller;
    }

    @FXML
    public Button button_a;

    @FXML
    public Button button_d;

    @FXML
    public Button button_b;

    @FXML
    public Button button_c;

    @FXML
    public TextArea text_fragen;

    @FXML
    public Button button_Quizgamequiz;

    @FXML
    private Button button_joker;

    @FXML
    private TextArea chat_fenster;

    @FXML
    private TextField chat_textfenster;

    @FXML
    private Button send_button_chat;

    @FXML
    private Label wrongAnswerLBL;

    @FXML
    private Label rightAnswerlbl;

    @FXML
    private Button nextQuestion;


    @FXML
    void nextQuestion(ActionEvent event) {
        wrongAnswerLBL.setText("");
        rightAnswerlbl.setText("");
        setNextQuestion();

    }

    @FXML
    void JokerClicked(ActionEvent event) {
        if (currentquestion.getCorrect_answer().equals((button_a.getText()))) {
            button_b.setText("");
            button_c.setText("");
        } else if (currentquestion.getCorrect_answer().equals((button_b.getText()))) {
            button_a.setText("");
            button_d.setText("");
        } else if (currentquestion.getCorrect_answer().equals((button_c.getText()))) {
            button_d.setText("");
            button_a.setText("");
        } else if (currentquestion.getCorrect_answer().equals((button_d.getText()))) {
            button_b.setText("");
            button_a.setText("");
        }
    }
    @FXML
    void buttonAClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
       this.answer(button_a.getText());

    }

    private void answer(String answer) {
            if (currentquestion.getCorrect_answer().equals(answer)){
            rightAnswerlbl.setText("Correct!");
            //Highscores speichern
            }else{
            String answerLabel = currentquestion.getCorrect_answer();
            wrongAnswerLBL.setText("That was the wrong answer :( - The right answer was:");
            rightAnswerlbl.setText(answerLabel);
                    }
       // setNextQuestion();

    }

    public void setNextQuestion() {
        List<Question> questions = QuestionRepository.getInstance().getQuestions();
        if (questions.isEmpty()) {
            button_Quizgamequiz.setStyle("-fx-background-color:orangered");
            nextQuestion.setDisable(true);
            button_joker.setDisable(true);



            //Highscores
        } else {
            currentquestion = questions.get(0);
            questions.remove(0);
            List<String> answers = Controller.shuffleAnswers(currentquestion);
            setData(answers, currentquestion.getQuestion());
        }
    }
    @FXML
    void buttonBClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(button_b.getText());
    }

    @FXML
    void buttonCClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(button_c.getText());
    }

    @FXML
    void buttonDClick(ActionEvent event) {
        wrongAnswerLBL.setText("");
        this.answer(button_c.getText());
    }

    @FXML
    void quitgamequiz(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Quiz_Menue.fxml"));

        Stage two = new Stage();
        two.setTitle("Quiz");
        two.setScene(new Scene(root));
        two.show();

        Stage stage = (Stage) button_Quizgamequiz.getScene().getWindow();
        stage.close();

    }

    public GamequizController() {

    }


    public void setData(List<String> answers, String question) {
        text_fragen.setText(question);

        button_a.setText(answers.get(0));

        button_b.setText(answers.get(1));

        button_c.setText(answers.get(2));

        button_d.setText(answers.get(3));
    }
}