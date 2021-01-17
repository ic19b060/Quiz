package fhtw;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class gameplay {

    static void play (JsonObject json) {


        JsonArray namearr = (JsonArray) json.get("results");

        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print

        for (Object objInArr : namearr) {
            JsonObject jsonFrage = (JsonObject) objInArr;
            List<JsonElement> answers = new ArrayList<>();
            answers.add(jsonFrage.get("correct_answer"));
            answers.add(jsonFrage.get("incorrect_answers"));
        }



/*
        String [] answer_list = new String[]{results.getCorrect_answer(), incorrect_results.getIncorrect_1(), incorrect_results.getIncorrect_2(), incorrect_results.getIncorrect_3()};
        System.out.println(answer_list[create_random_question_order.shuffle()]);
        System.out.println(Arrays.toString(answer_list));
*/
    }

    static class json_class{

        String category;
        String type;
        String difficulty;
        String question;
        String correct_answer;

        public String getCategory() {
            return category;
        }
        public String getType() {
            return type;
        }
        public String getDifficulty() {
            return difficulty;
        }
        public String getQuestion() {
            return question;
        }
        public String getCorrect_answer() {
            return correct_answer;
        }

        class incorrect_answers{

            String incorrect_1;
            String incorrect_2;
            String incorrect_3;

            public String getIncorrect_1() {
                return incorrect_1;
            }
            public String getIncorrect_2() {
                return incorrect_2;
            }
            public String getIncorrect_3() {
                return incorrect_3;
            }
        }
    }

    public static class create_random_question_order {
        public static int shuffle () {
            Random rd = new Random(); // creating Random object
            int upperbound = 4;
            //generate random values from 0-3
            int int_random = rd.nextInt(upperbound);
            return int_random;
        }
    }

}
