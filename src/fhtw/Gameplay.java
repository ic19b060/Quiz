package fhtw;

import com.google.gson.*;

import javax.xml.transform.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Gameplay {

    static void singleplay(JsonObject json) {

        Integer user_points = 0;

        //create Array of questions
        JsonArray questionArray = (JsonArray) json.get("results");

        //for each question:
        for (JsonElement question : questionArray){
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
            for (JsonElement answer : incorrect_answer){
                answers.add(answer);
            }
            System.out.println(jsonQuestion.get("question"));
            System.out.println(answers);

            //player answers

            String round_stats = get_answer(correct_answer, user_points);
            System.out.println(round_stats);
            System.out.println("The correct answer was: " + correct_answer + "\n");
            //
        }
    }


        public static int shuffle () {
            Random rd = new Random(); // creating Random object
            int upperbound = 4;
            //generate random values from 0-3
            int int_random = rd.nextInt(upperbound);
            return int_random;
        }

    static String get_answer(JsonElement correct_answer, Integer user_points) {

        Scanner user = new Scanner(System.in);
        String user_answer = user.nextLine();
        String message = null;

        //TODO Punktezahl (user points) speichern und übernehmen
        if (!user_answer.equals(correct_answer.toString())) {
            message = "Oh no, " + user_answer + " is the wrong answer!";
        } else {
            //idee: array mit messages erstellen von denen random eine geprinted wird
            message = "Correct answer! Points: ";
            user_points++;
        }

        return message ;
    }

}
