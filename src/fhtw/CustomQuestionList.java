package fhtw;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * A list for our Question-Objects for the Custom Game Mode
 * Custom questions are stored in the database collection "CustomGame"
 * Each Document in this Collection is a different game set, differentiated by the "name"
 * Inside the document, the Array "questions" stores the question information (each question is 1 object in this array)
 */
public class CustomQuestionList {

    @Expose
    private String name ="";
    @Expose
    private List<Question> questions = new ArrayList<>();

    public CustomQuestionList() {
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
