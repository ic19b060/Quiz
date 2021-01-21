package fhtw;

import java.util.List;
import java.util.Scanner;

public class Link {
    public static String Link() {



        List<Integer> values = Controller.get_values();

        int amount = values.get(0);
       int category = values.get(1);
       int difficulty2 = values.get(2);

        String difficulty = "";
        if (difficulty2 == 1){
            difficulty = "easy";

        }

        //System.out.println("Amount of questions: ");
        //String amount = create_amount();

        //System.out.println("Choose a category: 9 - 32, nothing = any");
        //String category = create_category();

        //System.out.println("Set difficulty: ");
        //String difficulty = create_difficulty();

        //only multiple choice question type
        String type = "&type=multiple";

        String link = "https://opentdb.com/api.php?" + amount + category + difficulty + type;
        System.out.println("Link: " + link);
        return link;
    }


    static String create_amount() {
        Scanner user = new Scanner(System.in);
        String amount = user.next();
        if (amount.length() != 0) {
            amount = "amount=" + amount;
        }
        return amount;
    }

    static String create_category() {
        String category = "";
        Scanner user = new Scanner(System.in);
        category = user.next();
        if (category.length() != 0) {
            category = "&category=" + category;
        }
        return category;
    }

    static String create_difficulty() {
        Scanner user = new Scanner(System.in);
        String difficulty = user.next();

        if (difficulty.length() != 0 && (difficulty.contains("easy") || difficulty.contains("medium")) || difficulty.contains("hard")){
            difficulty = "&difficulty=" + difficulty;
        }
        else if (difficulty.length() != 0) {
            System.out.println("Input Error!");
        }
        else {
            difficulty="";
        }
        return difficulty;
    }
}