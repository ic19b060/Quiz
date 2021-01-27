package fhtw;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;

/**
 * connects to the Database for our Quiz.
 * We need MongoDB for Login and Creating customized Questions
 */
public class MongoDB {

    public static MongoClient connectToDb() {
        return MongoClients.create(
                "mongodb+srv://admin:mongodbpw@cluster0.n568g.mongodb.net/quizDB?retryWrites=true&w=majority");
    }

    public static MongoDatabase getDB(MongoClient client){

        return client.getDatabase("quizDB");
    }


}
