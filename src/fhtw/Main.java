package fhtw;

import com.google.gson.JsonObject;
import static fhtw.QuestionHandle.Link;
import static fhtw.APIHandle.Json_complete;

public class Main {

    public static void main(String[] args) {

        //create link for question set based on user choice
        //connect with gui!
        String link = Link();

        //create question set with created link for API
        JsonObject questions = Json_complete(link);

        //gameplay logic

    }

}

