package fhtw;


public class Highscore {

    private String usernameHighscoreFromDB = PersonalData.getInstance().getUsername();
    private Integer highscoreFromDB = PersonalData.getInstance().getHighscore();

    public String getUsernameHighscoreFromDB() {
        return usernameHighscoreFromDB;
    }

    public void setUsernameHighscoreFromDB(String usernameHighscoreFromDB) {
        this.usernameHighscoreFromDB = usernameHighscoreFromDB;
    }

    public Integer getHighscoreFromDB() {
        return highscoreFromDB;
    }

    public void setHighscoreFromDB(Integer highscoreFromDB) {
        this.highscoreFromDB = highscoreFromDB;
    }
}
