package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            System.out.println("** Starting Maze Runner");
            Configuration config = new Configuration(args);//read configuration from command line
            BufferedReader reader = new BufferedReader(new FileReader(config.inputFile()));

            Maze maze = new Maze(config.inputFile());
            System.out.println("**** Reading the maze from file " + config.inputFile());
            maze.printMaze(logger, reader);

            ArrayList<Tile>[][] mazeArray = maze.mazeArray(reader); //store maze in array
            Tile start = maze.getStart(mazeArray);
            Tile end = maze.getEnd(mazeArray);

            String mazeSolution = maze.solution(mazeArray,start,end); //determine the path to the exit

            boolean valid = config.validPath(config.inputPath(), mazeSolution);//determine if an input path is valid


        } catch(Exception e){
            System.err.println("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }

}