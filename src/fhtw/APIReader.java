package fhtw;

import com.google.gson.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** //TODO
 * Nici bitte du erklären was da passiert :-D
 *
 */
public class APIReader {
    static JsonObject jsonComplete(String link) {

        //Pass the desired URL as an object:
        URL url = null;

        try {
            url = new URL(link);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        //Type cast the URL object into a HttpURLConnection object.
        // The benefit of doing this is that we will be able to harness the properties of the HttpURLConnection class to validate features.
        // For example, set the request type or check the status of the response code:
        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        //Set the request type, as in, whether the request to the API is a GET request or a POST request.
        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException | NullPointerException e) {
            e.printStackTrace();
        }

        //Open a connection stream to the corresponding API.
        //Get the corresponding response code.
        int responsecode = 0;
        try {
            conn.connect();
            responsecode = conn.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Now we need to perform a check so that if the response code is not 200, we throw a runtime exception, or otherwise carry on the rest of the procedure. The structure would be like this:
        JsonObject jsonObject = null;
        if (responsecode != 200) {
            throw new RuntimeException("Runtime Error");
        } else {

            String inline = null;
            try {
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline = sc.nextLine();
                }
                sc.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                jsonObject = JsonParser.parseString(inline).getAsJsonObject();
                JsonArray namearr = (JsonArray) jsonObject.get("results");

                Gson gson = new GsonBuilder().setPrettyPrinting().create(); // pretty print
                String prettyJson = gson.toJson(jsonObject);
                System.out.println(prettyJson);

                for (Object objInArr : namearr) {
                    JsonObject jsonquestion = (JsonObject) objInArr;
                    List<JsonElement> answers = new ArrayList<>();
                    answers.add(jsonquestion.get("correct_answer"));
                    answers.add(jsonquestion.get("incorrect_answers"));
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
        return jsonObject;
    }
}
