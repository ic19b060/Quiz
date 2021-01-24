package fhtw;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.Iterator;

public class getCustomGameName {


    public static String createCustomQuestionName(String collectionName) {

        //connect to database
        String userinput;

        try (MongoClient client = MongoDB.connectToDb()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoCollection customGameCollection = db.getCollection("CustomGame");

            //TODO get document name from user
            userinput = "";

            MongoCursor<Document> cursor = customGameCollection.find().iterator();
            try {
                while (cursor.hasNext()) {
                    Document current = cursor.next();
                    if (userinput.equals(current.get("name"))) {
                        //TODO Error Message
                    } else if ((!cursor.hasNext()) && (!userinput.equals(current.get("name")))) {
                        //TODO Message: successfully created
                    }
                }
            } finally {
                cursor.close();
            }
        }

        return userinput;
    }
}
//TODO LISL: add option in quizstart menu to choose custom collection
 /*   public static void showCustomCollections(){
        MongoDatabase database = MongoDB.connect_to_db();

        MongoIterable<String> customCollectionList = database.listCollectionNames();
        for (String name : customCollectionList) {
            if (!(name.equals("Users") && !name.equals("Highscores"))){
                System.out.println(name);
            }
        }
    }

}
        */
//Idea if we have time:
//Make existing custom question collections editable (show all questions, add and delete questions)
