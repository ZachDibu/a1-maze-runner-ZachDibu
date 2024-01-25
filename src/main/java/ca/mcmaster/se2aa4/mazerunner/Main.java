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
                display_solution(config);
                logger.info("Type \"-i\" or \"--input\" to enter a new maze file");
                logger.info("Type \"-p\" to try a new path");
                logger.info("Type \"-r\" to show solution from opposite direction");
                logger.info("Type anything else to end Maze Runner");
                String input = scanner.nextLine().toLowerCase();
                if (Objects.equals(input, "-i") || Objects.equals(input, "--input")) {
                    logger.info("Enter New File: ");
                    config.inputFile = scanner.nextLine();
                    logger.info("The new file is: " + config.inputFile);
                    config.mode = "MazeSolver";
                } else if (Objects.equals(input, "-p")) {
                    logger.info("Enter New Path: ");
                    config.inputPath = scanner.nextLine();
                    logger.info("The new path is: " + config.inputPath);
                    config.inputPath = config.inputPath.replace(" ", "").toUpperCase();
                    config.mode = "COMPARE";
                } else if (Objects.equals(input, "-r")){
                    config.mode = "REVERSE";
                } else {
                    break;
                }
            }while(true);

       } catch(Exception e){
           logger.error("/!\\ An error has occurred /!\\");
           return;
       }
        logger.info("** End of MazeRunner");
    }

    public static void display_solution(Configuration config) throws IOException{

        Maze maze = new Maze(config.inputFile);
        maze.printMaze();
        maze.mazeArray(); //store maze in array

        maze.getStart(maze.mazeArray); //get the starting tile
        maze.getEnd(maze.mazeArray); //get the final tile

        MazeSolution solution = new MazeSolution(maze.inputFile);

        solution.canonicalSolution(maze.mazeArray, maze.start, maze.end); //determine the canonical path to the exit
        solution.factorizedSolution = solution.factorizeSolution(solution.canonicalSolution);
        solution.reverseCanonical();
        solution.reverseFactorizedSolution = solution.factorizeSolution(solution.reverseCanonicalSolution);

        logger.info("**** Computing path");

        if (Objects.equals(config.mode,"MazeSolver")) {
            logger.info("Solution to maze: " + solution.factorizedSolution);
        }else if (Objects.equals(config.mode,"REVERSE")){
            logger.info("Solution from other end: " + solution.reverseFactorizedSolution);
        }else if (Objects.equals(config.mode,"COMPARE")) {
            solution.convertCanonical(config.inputPath.replace(" ", "")); //convert input path to canonical form
            PrimAlg alg = new PrimAlg(maze.mazeArray, maze.start, maze.end);
            String result = (alg.validate_path(solution.inputCanonical)) ? "Correct Path" : "Incorrect Path";
            logger.info(result);
        }
    }


}