package fhtw;

import com.google.gson.*;

import java.util.Random;

public class Gameplay {

    static void singleplay(JsonObject json) {

        Gson gson = new Gson();

        String json_string = json.toString();
        //Results[] resultArray = gson.fromJson(json,  Results[].class);

        /*
        for(Results result : resultArray) {
            System.out.println(result.getResults());
        }
        */


      /*  JsonArray namearr = (JsonArray) json.get("results");

        Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print

        for (Object objInArr : namearr) {
            JsonObject jsonFrage = (JsonObject) objInArr;
            List<JsonElement> answers = new ArrayList<>();
            answers.add(jsonFrage.get("correct_answer"));
            answers.add(jsonFrage.get("incorrect_answers"));
        }

*/

/*
        String [] answer_list = new String[]{results.getCorrect_answer(), incorrect_results.getIncorrect_1(), incorrect_results.getIncorrect_2(), incorrect_results.getIncorrect_3()};
        System.out.println(answer_list[create_random_question_order.shuffle()]);
        System.out.println(Arrays.toString(answer_list));
*/
    }


        public static int shuffle () {
            Random rd = new Random(); // creating Random object
            int upperbound = 4;
            //generate random values from 0-3
            int int_random = rd.nextInt(upperbound);
            return int_random;
        }


}
