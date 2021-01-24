package fhtw;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.Iterator;


public class CustomQuestions {


    public static String createCustomQuestionCollection(String collectionName) {

        //connect to database
        try (MongoClient client = MongoDB.connect_to_db()) {
            MongoDatabase db = MongoDB.getDB(client);
            MongoIterable<String> collections = db.listCollectionNames();

            for (String collection : collections) {

            }
            //check if collection is already in database
            Iterator<String> iterator = collections.iterator();
            while (iterator.hasNext()) {
                String current = iterator.next();
                if (current.equals(collectionName)) {
                    //TODO Error MEssage
                } else if ((!iterator.hasNext()) && (!current.equals(collectionName))) {
                    db.createCollection(collectionName);
                    //TODO Message: Collection successfully created
                }
            }
            return collectionName;
        }
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
