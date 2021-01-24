package fhtw;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {

    //sicherstellen dass es nur genau 1 gibt
    //singleton pattern
    private static final QuestionRepository instance = new QuestionRepository();

    public static QuestionRepository getInstance() {
        return instance;
    }

    private List <Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    private QuestionRepository() {

    }

}
