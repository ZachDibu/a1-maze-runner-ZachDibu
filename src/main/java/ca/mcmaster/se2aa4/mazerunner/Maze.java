package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    private String inputFile;
    public Maze(String inputFile) {
        this.inputFile = inputFile;
    }

    public String solution() {
        String solution = "FFFF";

        return solution;
    }

    public void printMaze(Logger logger) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    logger.info("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    logger.info("PASS ");
                }
            }
            logger.info(System.lineSeparator());
        }
    }
}
