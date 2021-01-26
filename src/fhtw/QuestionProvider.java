package fhtw;


import com.google.gson.annotations.SerializedName;

public class QuestionProvider {

    @SerializedName("category")
    private fhtw.enums.Category Category;

    Integer amount;
    String difficulty;
    String type;

    public QuestionProvider(Integer amount, String difficulty, String category, String type) {

        this.amount = amount;
        this.difficulty = difficulty;
        this.type = type;

        Category = Category.valueOf(category);
    }

    public String getApiPath() {

        return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.Category.getValue()
                + "&difficulty=" + this.difficulty
                + "&type=" + this.type;
           }

}
