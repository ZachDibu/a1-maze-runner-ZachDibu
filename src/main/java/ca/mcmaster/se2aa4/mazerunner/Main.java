package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            System.out.println("** Starting Maze Runner");
            Configuration config = new Configuration(args);//read configuration from command line
            Maze maze = new Maze(config.inputFile());
            String mazeSolution = maze.solution(); //determine the path to the exit
            boolean valid = config.validPath(config.inputPath(), maze.solution());//determine if an input path is valid
            System.out.println("**** Reading the maze from file " + config.inputFile());
            maze.mazeArray(); //store maze in array
            maze.printMaze(logger);


        } catch(Exception e){
            System.err.println("/!\\ An error has occured /!\\");
        }
        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }

}