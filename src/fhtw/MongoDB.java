package fhtw;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;

public class MongoDB {

    public static MongoDatabase connect_to_db() {

        MongoClient mongoClient = MongoClients.create(
                "mongodb+srv://admin:mongodbpw@cluster0.n568g.mongodb.net/quizDB?retryWrites=true&w=majority");

        MongoDatabase database = mongoClient.getDatabase("quizDB");

        return database;

        //Creating a collection
        //  database.createCollection("Test2");
        //System.out.println("Collection created successfully");

      //  MongoCollection<Document> highscore_collection = database.getCollection("Highscores");

        //List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
        //databases.forEach(db -> System.out.println(db.toJson()));

  /*      System.out.println("Highscores: ");
        MongoCursor<Document> cursor = highscore_collection.find().iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
*/
    }



}
