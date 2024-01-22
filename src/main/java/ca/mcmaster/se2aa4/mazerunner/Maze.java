package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sound.midi.SysexMessage;

public class Maze {

    public Tile start;
    public Tile end;
    private String inputFile;

    public int width = 0;
    public int height = 0;

    public String canonicalSolution;

    public Maze(String inputFile) {
        this.inputFile = inputFile;
    }


    public void printMaze(Logger logger) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();
        this.width = line.length();

        while (line != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    logger.info("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    logger.info("PASS ");
                }
            }
            logger.info(System.lineSeparator());
            this.height++;
            line = reader.readLine();
        }
        reader.close();
    }


    public Tile[][] mazeArray() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Tile[][] maze = new Tile[height][width];
        String line = reader.readLine();
        int y = 0;
        while (line != null && y < height){
            if (line.isEmpty() || line.isBlank()) {
                for (int x = 0; x < width; x++) {
                    maze[y][x] = new Tile("PATH", y, x);
                }
            }
            for (int x = 0; x < line.length() && x < width; x++) {
                if (line.charAt(x) == '#') {
                    maze[y][x] = new Tile("WALL",y,x);
                } else if (line.charAt(x) == ' ') {
                    maze[y][x] = new Tile("PATH",y,x);
                }
            }
            y++;
            line = reader.readLine();
        }
        reader.close();
        return maze;
    }

    public void getStart(Tile[][] mazeArray) {
        int x = 0;
        int y = 0;
        while (!mazeArray[y][x].type.equals("PATH")){
            y++;
        }
        this.start = mazeArray[y][x];
    }

    public void getEnd(Tile[][] mazeArray) {
        int x = width-1;
        int y = 0;
        while (!mazeArray[y][x].type.equals("PATH")){
            y++;
        }
        this.end = mazeArray[y][x];
    }


    public void solution(Tile[][] maze, Tile start, Tile end) {
        PrimAlg mazeAlgorithm = new PrimAlg(maze, start, end);
        Stack<String> stackSolution = mazeAlgorithm.solveMaze();

        String strSolution = "";
        Iterator value = stackSolution.iterator();

        while (value.hasNext()){
            strSolution = strSolution + value.next();
        }
        canonicalSolution = strSolution;
    }


}
