package fhtw;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class CustomQuestionCollection {
    @Expose
    private String name ="";
    @Expose
    private List<Question> questions = new ArrayList<>();

    public CustomQuestionCollection() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
