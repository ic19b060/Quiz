package fhtw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Personaldata {

    String username;
    Integer Highscore;
    Integer Jokeranzahl;
    Integer TempScore;
    //Highscoregesamt ??


    private static final Personaldata instance = new Personaldata();

    public static Personaldata getInstance() {
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
