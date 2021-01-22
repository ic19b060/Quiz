package fhtw;

import fhtw.enums.Category;

public class QuestionProvider {


    Integer amount;
    Enum category;
    String difficulty;
    String type;

    public QuestionProvider(Integer amount, String difficulty, String category, String type) {
        this.amount = amount;
       // this.category = category;
        this.difficulty = difficulty;
        this.type = type;

      //  Enum category = Category.valueOf()

    }

    public String getApiPath() {
         return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.category
                + "?difficulty=" + this.difficulty
                + "&type=" + this.type;

           }



}
