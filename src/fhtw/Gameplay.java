package fhtw;

import com.google.gson.*;

import java.util.*;

public class Gameplay {

    static void singleplay(JsonObject json) {

        //TODO Startbildschirm mit Nachricht oder so

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
            System.out.println(shuffle_answers(answers));

           /* String string_answers = (String) answers.toString();
            Collections.shuffle(Arrays.asList(string_answers));
            System.out.println(string_answers);

            List<String> rand_answers = Arrays.asList(string_answers);
            Collections.shuffle(Arrays.asList(rand_answers, new Random()));
            System.out.println(rand_answers);

*/

            //player answers

            String round_stats = get_answer(correct_answer, user_points);
            System.out.println(round_stats);
            System.out.println("The correct answer was: " + correct_answer + "\n");

            //TODO wenn Ende erreicht: Nachricht über Ende + Punktezahl
            //TODO Zufallselement Goodie(Joker) erhalten
        }
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

    public static List<JsonElement> shuffle_answers (List<JsonElement> answers) {

        List<JsonElement>  rand_answers = new ArrayList<>();

        for(JsonElement element : answers){
            rand_answers.add(element);
        }
        Collections.shuffle(rand_answers);
        return rand_answers;
    }
}
