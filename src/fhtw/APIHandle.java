//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fhtw;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class APIHandle {
    public APIHandle() {
    }

    static JsonObject Json_complete(String link) {
        URL url = null;

        try {
            url = new URL(link);
        } catch (MalformedURLException var14) {
            var14.printStackTrace();
        }

        HttpURLConnection conn = null;

        try {
            conn = (HttpURLConnection)url.openConnection();
        } catch (IOException var13) {
            var13.printStackTrace();
        }

        try {
            conn.setRequestMethod("GET");
        } catch (ProtocolException var12) {
            var12.printStackTrace();
        }

        int responsecode = 0;

        try {
            conn.connect();
            responsecode = conn.getResponseCode();
        } catch (IOException var11) {
            var11.printStackTrace();
        }

        JsonObject json_object = null;
        if (responsecode != 200) {
            throw new RuntimeException("Runtime Error");
        } else {
            String inline = null;

            try {
                Scanner sc;
                for(sc = new Scanner(url.openStream()); sc.hasNext(); inline = sc.nextLine()) {
                }

                //System.out.println("\n JSON data in string format");
                //System.out.println(inline);
                sc.close();
            } catch (IOException var16) {
                var16.printStackTrace();
            }

            try {
                json_object = JsonParser.parseString(inline).getAsJsonObject();
                JsonArray namearr = (JsonArray)json_object.get("results");
                Iterator var7 = namearr.iterator();

                while(var7.hasNext()) {
                    Object objInArr = var7.next();
                    JsonObject jsonFrage = (JsonObject)objInArr;
                    System.out.println(jsonFrage.get("question"));
                    List<JsonElement> answers = new ArrayList();
                    answers.add(jsonFrage.get("correct_answer"));
                    answers.add(jsonFrage.get("incorrect_answers"));
                    System.out.println(answers + "\n");
                }
            } catch (Exception var15) {
                System.out.println(var15.getMessage());
            }

            return json_object;
        }
    }
}
