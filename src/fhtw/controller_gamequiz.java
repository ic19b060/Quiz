package fhtw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;


public class controller_gamequiz {

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
    void button_a_click(ActionEvent event) {
        List<Question> questions = QuestionRepository.getInstance().getQuestions();
        currentquestion = questions.get(1);
        List<String> answers = Controller.shuffle_answers(currentquestion);
        setData(answers.get(0), answers.get(1), answers.get(2), answers.get(3),currentquestion.getQuestion() );
    }

    @FXML
    void button_b_click(ActionEvent event) {
        List<Question> questions = QuestionRepository.getInstance().getQuestions();
        currentquestion = questions.get(2);
        List<String> answers = Controller.shuffle_answers(currentquestion);
        setData(answers.get(0), answers.get(1), answers.get(2), answers.get(3),currentquestion.getQuestion() );
    }

    @FXML
    void button_c_click(ActionEvent event) {

    }

    @FXML
    void button_d_click(ActionEvent event) {

    }

    @FXML
    void quitgamequiz(ActionEvent event) {

    }

    public controller_gamequiz() {

    }

    public void setData(String aString, String cString , String bString , String dString, String zString) {

        text_fragen.setText(zString);

        button_a.setText(aString);

        button_b.setText(bString);

        button_c.setText(cString);

     button_d.setText(dString);
    }
}