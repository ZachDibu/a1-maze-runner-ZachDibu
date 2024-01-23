package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;
import java.util.Scanner;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.Level;


public class Main {

    private static final Logger logger = LogManager.getLogger();

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Configurator.setAllLevels(LogManager.getRootLogger().getName(), Level.ALL);

        logger.info("** Starting Maze Runner");
        try{

            Configuration config = new Configuration(args);//read configuration from command line
            config.setInputFile();
            config.setInputPath();
            config.setMode();

            do {
                Maze maze = display_solution(config);
                display_path(config, maze);

                String input;
                logger.info("Type \"-i\" or \"--input\" to enter a new maze file");
                logger.info("Type \"-p\" to try a new path");
                logger.info("Type anything else to end Maze Runner");
                input = scanner.nextLine().toLowerCase();
                if (Objects.equals(input, "-i") || Objects.equals(input, "--input")) {
                    logger.info("Enter New File: ");
                    input = scanner.nextLine();
                    config.inputFile = input;
                    logger.info("The new file is: " + config.inputFile);
                    config.mode = "MazeSolver";

                } else if (Objects.equals(input, "-p")) {
                    logger.info("Enter New Path: ");
                    input = scanner.nextLine();
                    config.inputPath = input.replace(" ", "");
                    logger.info("The new path is: " + config.inputPath);
                    config.mode = "COMPARE";
                } else{
                    break;
                }

            }while(true);

       } catch(Exception e){
           logger.error("/!\\ An error has occured /!\\");
           return;
       }

        logger.info("** End of MazeRunner");
    }

    public static Maze display_solution(Configuration config){
        Maze maze = new Maze(config.inputFile);
        try {
            maze.printMaze();

            Tile[][] mazeArray = maze.mazeArray(); //store maze in array

            maze.getStart(mazeArray); //get the starting tile
            maze.getEnd(mazeArray); //get the final tile

            maze.setCanonicalSolution(mazeArray, maze.start, maze.end); //determine the canonical path to the exit
            maze.convertCanonical();
            maze.factorizedSolution = maze.factorizeSolution(maze.canonicalSolution);
            maze.reverseCanonical();
            maze.reverseFactorizedSolution = maze.factorizeSolution(maze.reverseCanonicalSolution);

            logger.info("**** Computing path");

            if (Objects.equals(config.mode,"MazeSolver")) {
                logger.info("Solution to maze: " + maze.factorizedSolution);
                logger.info("Solution from other end: " + maze.reverseFactorizedSolution);
            }
        }catch(Exception e){
            logger.error("/!\\ An error has occured /!\\");
        }
        return maze;
    }

    public static void display_path(Configuration config, Maze maze){
        if (Objects.equals(config.mode,"COMPARE")) {
            boolean validCanon = config.validPath(config.inputPath, maze.canonicalSolution);//determine if an input path is valid
            boolean validFact = config.validPath(config.inputPath, maze.factorizedSolution.replace(" ", ""));
            String result = (validCanon || validFact) ? "Correct Path" : "Incorrect Path";
            logger.info(result);
        }
    }

}