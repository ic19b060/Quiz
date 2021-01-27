package fhtw;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * We parse the Questions from API into Class Question (List<Question>)
 *
 * We get all answers & incorrect answers from this class to compare with the users input.
 *
 */
public class Question {

    //This is our question that we get from the API
    @Expose
    private String category;
    @Expose
    private String type;
    @Expose
    private String question;
    @Expose
    private String difficulty;
    @Expose
    private String correctAnswer;
    @Expose
    private List<String> incorrectAnswers;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

}


