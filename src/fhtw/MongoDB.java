package fhtw;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoDB {

    MongoClientURI uri = new MongoClientURI(
            "mongodb+srv://admin:<password>@cluster0.n568g.mongodb.net/<dbname>?retryWrites=true&w=majority");

    MongoClient mongoClient = new MongoClient(uri);
    MongoDatabase database = mongoClient.getDatabase("test");


}
