package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
       try{
            System.out.println("** Starting Maze Runner");

            Configuration config = new Configuration(args);//read configuration from command line
            config.inputFile();
            config.inputPath();
            config.getMode();

            BufferedReader reader = new BufferedReader(new FileReader(config.inputFile));
            Maze maze = new Maze(config.inputFile);
            System.out.println("**** Reading the maze from file " + config.inputFile);
            maze.printMaze(logger, reader);

            ArrayList<Tile>[][] mazeArray = maze.mazeArray(reader); //store maze in array
            System.out.println(mazeArray[0][0]);
            System.out.println(maze.width);
            System.out.println(maze.height);



           System.out.println("8");
            maze.getStart(mazeArray);

            System.out.println("9");
            maze.getEnd(mazeArray);

            System.out.println("10");
            String mazeSolution = maze.solution(mazeArray,maze.start,maze.end); //determine the path to the exit


            System.out.println("**** Computing path");
            if (Objects.equals(config.mode,"MazeSolver")) {
                System.out.println("Solution to maze: " + mazeSolution);
            }else{
                boolean valid = config.validPath(config.inputPath, mazeSolution);//determine if an input path is valid
                String result = valid? "Correct Path" : "Incorrect Path";
                System.out.println(result);
            }

       } catch(Exception e){
           System.err.println("/!\\ An error has occured /!\\");
           e.printStackTrace();
       }

        System.out.println("** End of MazeRunner");
    }

}