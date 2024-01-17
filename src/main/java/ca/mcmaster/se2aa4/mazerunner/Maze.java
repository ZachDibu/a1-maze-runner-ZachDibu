package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    public Object start;
    public Object end;
    public Object direction;
    private String inputFile = null;

    private int width = 0;
    private int height = 0;

    public Maze(String inputFile) {
        this.inputFile = inputFile;
    }

    public String solution() {
        PrimAlg alg = new PrimAlg(mazeArray());
        return alg.solution;
    }

    public void printMaze(Logger logger) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                    width++;
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                    width++;
                }
            }
            System.out.print(System.lineSeparator());
            height++;
        }
    }

    public ArrayList<Tile>[][] mazeArray(){
        String type = null;
        Tile tile = new Tile(type);
        ArrayList<Tile>[][] map = new ArrayList[width][height];
        for (int c = 0; c != width; c++){
            for (int h = 0; h != height; h++){

            }
        }


        return map;
    }
}
