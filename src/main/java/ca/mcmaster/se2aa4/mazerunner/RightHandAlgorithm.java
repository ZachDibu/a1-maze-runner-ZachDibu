package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;
import java.util.Stack;

public class RightHandAlgorithm implements MazeAlgorithm {
    Tile start;
    Tile end;

    private final Tile[][] maze;

    public RightHandAlgorithm(Tile[][] maze, Tile start, Tile end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    @Override
    public Stack<String> solveMaze(){
        MazeRunner runner = new MazeRunner(start);
        Stack<String> solution = new Stack<>();

        //first move is always forward
        runner.move(maze[runner.currentPosition.yCord][runner.currentPosition.xCord + 1]);
        solution.push("F");

        //find end of maze
        while (!Objects.equals(runner.currentPosition, end)) {
            runner.currentPosition.passed = true;
            Tile S = maze[runner.currentPosition.yCord + 1][runner.currentPosition.xCord];//tile SOUTH
            Tile W = maze[runner.currentPosition.yCord][runner.currentPosition.xCord - 1];//tile WEST
            Tile N = maze[runner.currentPosition.yCord - 1][runner.currentPosition.xCord];//tile NORTH
            Tile E = maze[runner.currentPosition.yCord][runner.currentPosition.xCord + 1];//tile EAST

            //based off of current direction, check to the right
            switch (runner.direction){
                case "EAST":
                    if (Objects.equals(S.type,"PATH")){
                        runner.move(S);
                        runner.direction = "SOUTH";
                        solution.push("RF");
                        break;
                    } else if (Objects.equals(E.type,"PATH")){
                        runner.move(E);
                        solution.push("F");
                        break;
                    } else if (Objects.equals(N.type,"PATH")){
                        runner.move(N);
                        runner.direction = "NORTH";
                        solution.push("LF");
                        break;
                    } else {
                        runner.move(W);
                        runner.direction = "WEST";
                        solution.push("RRF");
                        break;
                    }

                case "SOUTH":
                    if (Objects.equals(W.type,"PATH")){
                        runner.move(W);
                        runner.direction = "WEST";
                        solution.push("RF");
                        break;
                    } else if (Objects.equals(S.type,"PATH")){
                        runner.move(S);
                        solution.push("F");
                        break;
                    } else if (Objects.equals(E.type,"PATH")){
                        runner.move(E);
                        runner.direction = "EAST";
                        solution.push("LF");
                        break;
                    } else {
                        runner.move(N);
                        runner.direction = "NORTH";
                        solution.push("RRF");
                        break;
                    }

                case "WEST":
                    if (Objects.equals(N.type,"PATH")){
                        runner.move(N);
                        runner.direction = "NORTH";
                        solution.push("RF");
                        break;
                    } else if (Objects.equals(W.type,"PATH")){
                        runner.move(W);
                        solution.push("F");
                        break;
                    } else if (Objects.equals(S.type,"PATH")){
                        runner.move(S);
                        runner.direction = "SOUTH";
                        solution.push("LF");
                        break;
                    } else {
                        runner.move(E);
                        runner.direction = "EAST";
                        solution.push("RRF");
                        break;
                    }

                case "NORTH":
                    if (Objects.equals(E.type,"PATH")){
                        runner.move(E);
                        runner.direction = "EAST";
                        solution.push("RF");
                        break;
                    } else if (Objects.equals(N.type,"PATH")){
                        runner.move(N);
                        solution.push("F");
                        break;
                    } else if (Objects.equals(W.type,"PATH")){
                        runner.move(W);
                        runner.direction = "WEST";
                        solution.push("LF");
                        break;
                    } else {
                        runner.move(S);
                        runner.direction = "SOUTH";
                        solution.push("RRF");
                        break;
                    }
            }
        }
        return solution;
    }

    @Override
    public boolean validate_path(String inputPath){
        MazeRunner runner = new MazeRunner(start);
        String direction = "EAST";
        for (int i = 0; i < inputPath.length(); i++){
            switch (inputPath.charAt(i)) {
                case 'F':
                    switch (direction){
                        case "EAST": runner.move(maze[runner.currentPosition.yCord][runner.currentPosition.xCord + 1]); break;
                        case "NORTH": runner.move(maze[runner.currentPosition.yCord - 1][runner.currentPosition.xCord]); break;
                        case "WEST": runner.move(maze[runner.currentPosition.yCord][runner.currentPosition.xCord - 1]); break;
                        case "SOUTH": runner.move(maze[runner.currentPosition.yCord + 1][runner.currentPosition.xCord]); break;
                    }
                    break;
                case 'L':
                    direction = switch (direction) {
                        case "EAST" -> "NORTH";
                        case "NORTH" -> "WEST";
                        case "WEST" -> "SOUTH";
                        case "SOUTH" -> "EAST";
                        default -> direction;
                    };
                    break;
                case 'R':
                    direction = switch (direction) {
                        case "EAST" -> "SOUTH";
                        case "NORTH" -> "EAST";
                        case "WEST" -> "NORTH";
                        case "SOUTH" -> "WEST";
                        default -> direction;
                    };
                    break;
            }
            if (runner.currentPosition.type.equals("WALL")){return false;}
        }
        return Objects.equals(runner.currentPosition, end);
    }

}
