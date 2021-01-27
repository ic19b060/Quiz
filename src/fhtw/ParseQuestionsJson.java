package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * We parse the Question Array we got from JSON into an ArrayList
 * "results" is an Array of JsonObjects, each Object is a question, including the answer alternatives, category, question itself etc.
 * we loop through the questions and store the information in a list consisting of our question class
 *
 */

public class ParseQuestionsJson {

    //create Array of questions
    static List<Question> parseQuestionJson(JsonObject json) {

        JsonArray questionArray = (JsonArray) json.get("results");
        List<Question> questions = new ArrayList<>();

        for (JsonElement question : questionArray) {

            List<String> answers = new ArrayList<>();
            Question cur = new Question();
            JsonObject jsonQuestion = (JsonObject) question;
            JsonArray wrongAnswer = jsonQuestion.get("incorrect_answers").getAsJsonArray();
            for (JsonElement jsonElement : wrongAnswer) {
               answers.add(jsonElement.getAsString());
            }
            cur.setCorrectAnswer(jsonQuestion.get("correct_answer").getAsString());
            cur.setIncorrectAnswers(answers);
            cur.setQuestion(jsonQuestion.get("question").getAsString());
            questions.add(cur);
            //create Object for question
        }

        return questions;
    }


    static List<Question> parseCustomQuestionJson(JsonObject json) {

        JsonArray questionArray = (JsonArray) json.get("questions");
        List<Question> questions = new ArrayList<>();

        for (JsonElement question : questionArray) {

            List<String> answers = new ArrayList<>();
            Question cur = new Question();
            JsonObject jsonQuestion = (JsonObject) question;
            JsonArray wrongAnswer = jsonQuestion.get("incorrectAnswers").getAsJsonArray();
            for (JsonElement jsonElement : wrongAnswer) {
                answers.add(jsonElement.getAsString());
            }
            cur.setCorrectAnswer(jsonQuestion.get("correctAnswer").getAsString());
            cur.setIncorrectAnswers(answers);
            cur.setQuestion(jsonQuestion.get("question").getAsString());
            questions.add(cur);
            //create Object for question
        }
        System.out.println("Questions: (parseQuestionsJson");
        System.out.println(questions);

        return questions;
    }


    }



