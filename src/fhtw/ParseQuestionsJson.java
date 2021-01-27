package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * We parse the Question Array we got from JSON into an ArrayList
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




    }



