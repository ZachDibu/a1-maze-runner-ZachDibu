package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
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
                MazeSolution solution = display_solution(config);
                display_path(config, solution);

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
           logger.error("/!\\ An error has occurred /!\\");
           return;
       }

        logger.info("** End of MazeRunner");
    }

    public static MazeSolution display_solution(Configuration config) throws IOException{
        Maze maze = new Maze(config.inputFile);

        maze.printMaze();

        Tile[][] mazeArray = maze.mazeArray(); //store maze in array

        maze.getStart(mazeArray); //get the starting tile
        maze.getEnd(mazeArray); //get the final tile

        MazeSolution solution = new MazeSolution(maze.inputFile);

        solution.canonicalSolution(mazeArray, maze.start, maze.end); //determine the canonical path to the exit
        solution.convertCanonical();
        solution.factorizedSolution = solution.factorizeSolution(solution.canonicalSolution);
        solution.reverseCanonical();
        solution.reverseFactorizedSolution = solution.factorizeSolution(solution.reverseCanonicalSolution);

        logger.info("**** Computing path");

        if (Objects.equals(config.mode,"MazeSolver")) {
            logger.info("Solution to maze: " + solution.factorizedSolution);
            logger.info("Solution from other end: " + solution.reverseFactorizedSolution);
        }

        return solution;
    }

    public static void display_path(Configuration config, MazeSolution solution){
        if (Objects.equals(config.mode,"COMPARE")) {
            boolean validCanon = config.validPath(config.inputPath, solution.canonicalSolution);//determine if an input path is valid
            boolean validFact = config.validPath(config.inputPath, solution.factorizedSolution.replace(" ", ""));
            String result = (validCanon || validFact) ? "Correct Path" : "Incorrect Path";
            logger.info(result);
        }
    }

}