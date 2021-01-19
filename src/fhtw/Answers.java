package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    //create Array of questions

    static void create_answer_list (JsonObject json) {

        JsonArray questionArray = (JsonArray) json.get("results");

        for (JsonElement question : questionArray) {
            //create Object for question
            JsonObject jsonQuestion = (JsonObject) question;
            //create list to store all possible answers
            List<JsonElement> answers = new ArrayList<>();
            //first store the correct answer
            JsonElement correct_answer = jsonQuestion.get("correct_answer");
            answers.add(correct_answer);
            //create a new Array for the nested values of the incorrect answers
            JsonArray incorrect_answer = (JsonArray) jsonQuestion.get("incorrect_answers");
            //for each from the incorrect answers add them to our answers-Array
            for (JsonElement answer : incorrect_answer) {
                answers.add(answer);
            }
            System.out.println(shuffle_answers(answers));
        }

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



