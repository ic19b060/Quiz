package fhtw;


import com.google.gson.annotations.SerializedName;

/**
 *
 * creates the path to API with all set parameters (we got from gui)
 *
 */
public class QuestionProvider {

    @SerializedName("category")
    private fhtw.enums.Category Category;

    Integer amount;
    String difficulty;
    String type;

    /**
     * Sets values from gui
     *
     * @param amount of questions
     * @param difficulty of questions
     * @param category general category
     * @param type is always multiple
     */
    public QuestionProvider(Integer amount, String difficulty, String category, String type) {

        this.amount = amount;
        this.difficulty = difficulty;
        this.type = type;

        Category = Category.valueOf(category);
    }

    /**
     * Creates the path for API.
     *
     * @return API_Path
     */
    public String getApiPath() {


        String category = this.Category.toString();
        String difficulty = this.difficulty;

        if (category == null) {
            category = "";
        } else {
            category = "&category=" + this.Category.getValue();
        }

        if (difficulty == null) {
            difficulty = "";
        } else {
            difficulty = "&difficulty=" + this.difficulty;
        }

        return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + category
                + difficulty
                + "&type=" + this.type;
           }

}
