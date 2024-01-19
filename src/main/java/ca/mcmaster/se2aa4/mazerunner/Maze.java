package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Maze {

    public Tile start;
    public Tile end;
    public Object direction;
    private String inputFile = null;

    public int width = 0;
    public int height = 0;

    public Maze(String inputFile) {
        this.inputFile = inputFile;
    }


    public void printMaze(Logger logger, BufferedReader reader) throws IOException {
        String line;
        line = reader.readLine();
        this.width = line.length();

        while (line != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("WALL ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("PASS ");
                }
            }
            System.out.print(System.lineSeparator());
            this.height++;
            line = reader.readLine();
        }
    }


    public ArrayList<Tile>[][] mazeArray(BufferedReader reader) throws IOException{
        ArrayList<Tile>[][] maze = new ArrayList[width][height];
        String line;
        int x = 0;
        int y = 0;

        while ((line = reader.readLine()) != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                maze[x][y] = new ArrayList<>();

                if (line.charAt(idx) == '#') {
                    Tile newTile = new Tile("WALL",x,y);
                    maze[x][y].add(newTile);
                    x++;
                } else if (line.charAt(idx) == ' ') {
                    Tile newTile = new Tile("PATH",x,y);
                    maze[x][y].add(newTile);
                    x++;
                }
            }
            x = 0;
            y++;
        }
        return maze;
    }

    public void getStart(ArrayList<Tile>[][] mazeArray) {
        int x = 0;
        int y = 0;
        while (!mazeArray[x][y].get(0).type.equals("PATH")){
            y++;
        }
        this.start = mazeArray[x][y].get(0);
    }

    public void getEnd(ArrayList<Tile>[][] mazeArray) {
        int x = width;
        int y = 0;
        while (!mazeArray[x][y].get(0).type.equals("PATH")){
            y++;
        }
        this.end = mazeArray[x][y].get(0);
    }


    public String solution(ArrayList<Tile>[][] maze, Tile start, Tile end) {
        PrimAlg mazeAlgorithm = new PrimAlg(maze, start, end);
        Stack<String> stackSolution = mazeAlgorithm.solveMaze();

        String strSolution = "";
        Iterator value = stackSolution.iterator();

        while (value.hasNext()){
            strSolution = strSolution + value.next();
        }
        return strSolution;
    }


}
