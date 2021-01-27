package fhtw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for Users personal data
 *
 */
public class PersonalData {

    String username;
    Integer Highscore;
    Integer joker;
    Integer tempScore;
    //Highscoregesamt ??


    private static final PersonalData instance = new PersonalData();

    public static PersonalData getInstance() {
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws IOException {
        this.username = username;
    }

    public Integer getHighscore() {
        return Highscore;
    }

    public void setHighscore(Integer highscore) {
        Highscore = highscore;
    }

    public Integer getJoker() {
        return joker;
    }

    public void setJoker(Integer joker) {
        this.joker = joker;
    }

    public Integer getTempScore() {
        return tempScore;
    }

    public void setTempScore(Integer tempScore) {
        this.tempScore = tempScore;
    }

    /**
     * Write Highscores from User in txt.File
     *
     * @throws IOException
     */
    public void writerdatainFile() throws IOException {
        File newFile = new File("Highscores.txt");
        FileWriter out = new FileWriter(newFile.getAbsoluteFile(), true);
        BufferedWriter writer = new BufferedWriter(out);
        writer.write("User: \t" + username + "\n");
        writer.write("Highscore: \t" + Highscore + "\n");
        writer.write("****************************************" + "\n");
        writer.close();
        out.close();
    }


}
