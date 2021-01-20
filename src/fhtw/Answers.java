package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    //create Array of questions

    static List<Question> parseQuestionJson(JsonObject json) {

        JsonArray questionArray = (JsonArray) json.get("results");
        List<Question> questions = new ArrayList<>();

        for (JsonElement question : questionArray) {

            List<String> answers = new ArrayList<>();
            Question cur = new Question();
            JsonObject jsonQuestion = (JsonObject) question;
            JsonArray wrong_answer = jsonQuestion.get("incorrect_answers").getAsJsonArray();
            for (JsonElement jsonElement : wrong_answer) {
               answers.add(jsonElement.getAsString());
            }
            cur.setCorrect_answer(jsonQuestion.get("correct_answer").getAsString());
            cur.setIncorrect_answers(answers);
            cur.setQuestion(jsonQuestion.get("question").getAsString());
            questions.add(cur);
            //create Object for question

        }

        return questions;
    }


    public static List<JsonElement> shuffle_answers (List<JsonElement> answers) {

        List<JsonElement>  rand_answers = new ArrayList<>();

        for(JsonElement element : answers){
            rand_answers.add(element);
        }
        Collections.shuffle(rand_answers);
        return rand_answers;
    }
}



