package fhtw;

import java.util.Scanner;

public class QuestionHandle {
    public static String Link() {

        System.out.println("Amount of questions: ");
        String amount = create_amount();

        //System.out.println("Choose a category: ");
       // String category = create_amount();

        //System.out.println("Set difficulty: ");
       // String difficulty = create_amount();

        //System.out.println("Choose question type: ");
       // String type = create_amount();

        String link = "https://opentdb.com/api.php?" + amount;
                //+ category + difficulty + type;
        //System.out.println("Link: " + link);
        return link;
    }


    //TODO check if Int
    //TODO methods for other settings - override?
    static String create_amount() {
        Scanner user = new Scanner(System.in);
        String amount = user.next();
        if (amount.length() != 0) {
            amount = "amount=" + amount;
        }
        return amount;
    }
}