package fhtw;

import java.util.Scanner;

public class QuestionHandle {
    public static String Link() {

        System.out.println("Amount of questions: ");
        String amount = create_amount();

        System.out.println("Choose a category: 9 - 32, nothing = any");
        String category = create_category();

        System.out.println("Set difficulty: ");
        String difficulty = create_difficulty();

        //only multiple choice question type
        String type = "&type=multiple";

        String link = "https://opentdb.com/api.php?" + amount + category + difficulty + type;
        System.out.println("Link: " + link);
        return link;
    }


    //TODO user input: check if Int
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