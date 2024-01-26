package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public interface MazeAlgorithm {
    Stack<String> solveMaze();

    boolean validate_path(String inputPath);
}
