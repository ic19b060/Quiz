import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import fhtw.MongoDB;
import fhtw.Question;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class CustomQuestions {

    public static String createCustomQuestionCollection() {

        //connect to database
        MongoDatabase database = MongoDB.connect_to_db();

        //TODO LISL: interface for creating custom questions and answers
        //first: create collection name (customCollectionName)
        //TODO NICI: check if collection name doesn't already exist in database
        //next interface for entering infos: e.g. 5 Textfields: Question, correct answer, incorrect answers
        //additional buttons: "submit question" (insert question into database) and "done" (return to home interface or whatever)

        //for Lisl:
        //String customCollectionName = buttonname.getText();

        //create collection
        //database.createCollection(customCollectionName);

        return "customCollectionName";
    }

    public static void createCustomQuestions(String customCollectionName){

        MongoDatabase database = MongoDB.connect_to_db();
        MongoCollection<Document> custom_question_collection = database.getCollection(customCollectionName);

        //for Lisl:
        //String customQuestion = buttonname.getText();
        //String customCorrectAnswer = buttonname.getText();
        //String customIncorrectAnswer1 = buttonname.getText();
        //String customIncorrectAnswer2 = buttonname.getText();
        //String customIncorrectAnswer3 = buttonname.getText();

        List<String> customIncorrectAnswerList = new ArrayList<>();
        //customIncorrectAnswerList.addAll(customIncorrectAnswer1, customIncorrectAnswer2, customIncorrectAnswer3);

        Document newCustomQuestion = new Document();
       /* newCustomQuestion.append("question", customQuestion);
        newCustomQuestion.append("correct_answer", customCorrectAnswer);
        newCustomQuestion.append("incorrect_answers", customIncorrectAnswerList);
       */
        //Inserting the document into the collection
        custom_question_collection.insertOne(newCustomQuestion);

    }

    public static void showCustomCollections(){
        MongoDatabase database = MongoDB.connect_to_db();

        MongoIterable<String> customCollectionList = database.listCollectionNames();
        for (String name : customCollectionList) {
            if (!(name.equals("Users") && name.equals("Highscores"))){
                System.out.println(name);
            }
        }

    }

}

//Idea if we have time:
//Make existing custom question collections editable (show all questions, add and delete questions)
