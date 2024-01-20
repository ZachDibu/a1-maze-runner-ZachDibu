package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);

        try{
            logger.info("** Starting Maze Runner");

            Configuration config = new Configuration(args);//read configuration from command line
            config.inputFile();
            config.inputPath();
            config.getMode();

            Maze maze = new Maze(config.inputFile);
            logger.info("**** Reading the maze from file " + config.inputFile);
            maze.printMaze(logger);

            Tile[][] mazeArray = maze.mazeArray(); //store maze in array

            maze.getStart(mazeArray); //get the starting tile
            maze.getEnd(mazeArray); //get the final tile

            String mazeSolution = maze.solution(mazeArray,maze.start,maze.end); //determine the path to the exit


            logger.info("**** Computing path");
            if (Objects.equals(config.mode,"MazeSolver")) {
                logger.info("Solution to maze: " + mazeSolution);
            }else{
                boolean valid = config.validPath(config.inputPath, mazeSolution);//determine if an input path is valid
                String result = valid? "Correct Path" : "Incorrect Path";
                logger.info(result);
            }

       } catch(Exception e){
           logger.error("/!\\ An error has occured /!\\");
       }

        logger.info("** End of MazeRunner");
    }

}