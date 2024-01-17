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


    public void printMaze(Logger logger, BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                    this.width++;
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                    this.width++;
                }
            }
            System.out.print(System.lineSeparator());
            this.height++;
        }
    }


    public ArrayList<Tile>[][] mazeArray(BufferedReader reader) throws IOException{
        ArrayList<Tile>[][] maze = new ArrayList[width][height];
        String line;
        int x = 0;
        int y = 0;

        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    maze[x][y].add(new Tile("WALL",x,y));
                    x++;
                } else if (line.charAt(idx) == ' ') {
                    maze[x][y].add(new Tile("PATH",x,y));
                    x++;
                }
            }
            x = 0;
            y++;
        }
        return maze;
    }

    public Tile getStart(ArrayList<Tile>[][] mazeArray) {
        int x = 0;
        int y = 0;
        while (mazeArray[x][y].get(0).type != "PATH"){
            y++;
        }
        return mazeArray[x][y].get(0);
    }

    public Tile getEnd(ArrayList<Tile>[][] mazeArray) {
        int x = width;
        int y = 0;
        while (mazeArray[x][y].get(0).type != "PATH"){
            y++;
        }
        return mazeArray[x][y].get(0);
    }


    public String solution(ArrayList<Tile>[][] maze, Tile start, Tile end) {
        PrimAlg alg = new PrimAlg(maze, start, end);
        return alg.solveMaze();
    }


}
