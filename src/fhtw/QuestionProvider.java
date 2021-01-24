package fhtw;


import com.google.gson.annotations.SerializedName;
import fhtw.enums.getCategory;

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
    }

    public String getApiPath() {

        return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.Category.value()
                + "&difficulty=" + this.difficulty
                + "&type=" + this.type;
           }

}
