package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;
//implements a method to solve a maze and to solve a maze
public interface MazeAlgorithm {
    Stack<String> solveMaze();

    boolean validate_path(String inputPath);
}
