package fhtw;

public class QuestionProvider {


    Integer amount;
    String category;
    String difficulty;
    String type;

    public QuestionProvider(Integer amount, String category, String difficulty, String type) {
        this.amount = amount;
        this.category = category;
        this.difficulty = difficulty;
        this.type = type;


    }

    public String getApiPath() {
         return "https://opentdb.com/api.php"
                + "?amount=" + this.amount.toString()
                + "&category=" + this.category
                + "?difficulty=" + this.difficulty
                + "&type=" + this.type;

           }



}
