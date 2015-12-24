package com.americanawesomealliance;

import com.badlogic.gdx.Gdx;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Brannon on 10/6/2015.
 */
public class HighScoreText {

    public HighScoreText() {
        System.out.println("HIgh Score Text initialized");

    }

    public void textWriter(int highScore) throws IOException {

        File temp = new File("./High Score Directory/High Score.txt");
        PrintWriter writer = new PrintWriter(temp);
        writer.print(Integer.toString(highScore));
        writer.flush();
    }

}
