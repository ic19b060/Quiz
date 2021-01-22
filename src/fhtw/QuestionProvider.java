package fhtw;


import com.google.gson.annotations.SerializedName;
import fhtw.enums.getCategory;

import java.util.Locale;

public class QuestionProvider {

    @SerializedName("category")
    private getCategory.Category Category;

    Integer amount;
    String difficulty;
    String type;


    public QuestionProvider(Integer amount, String difficulty, String category, String type) {
        this.amount = amount;
        this.difficulty = difficulty;
        this.type = type;


        Category = getCategory.Category.valueOf(category);
        System.out.println(amount);
        System.out.println(difficulty);
        System.out.println(Category.value());


    }

    public String getApiPath() {
        System.out.println( "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.Category.value()
                + "?difficulty=" + this.difficulty
                + "&type=" + this.type);

        return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.Category.value()
                + "&difficulty=" + this.difficulty
                + "&type=" + this.type;

           }



}
