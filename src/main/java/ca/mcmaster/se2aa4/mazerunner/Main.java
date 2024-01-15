package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        try {
            logger.info("** Starting Maze Runner");
            Configuration config = new Configuration(args);//read configuration from command line
            Maze maze = new Maze(config.inputFile());
            String mazeSolution = maze.solution(); //determine the path to the exit
            boolean valid = config.validPath(config.inputPath(), maze.solution());//determine if an input path is valid
            logger.info("**** Reading the maze from file " + config.inputFile());
            maze.printMaze(logger);

        } catch(Exception e){
            logger.error("/!\\ An error has occured /!\\");
        }
    }
}