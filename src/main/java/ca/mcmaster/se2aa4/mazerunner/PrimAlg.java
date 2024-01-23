package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;
import java.util.Stack;

public class PrimAlg {
    Tile start;
    Tile end;

    private final Tile[][] maze;

    public PrimAlg(Tile[][] maze, Tile start, Tile end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    public Stack<String> solveMaze(){
        MazeRunner runner = new MazeRunner(start);
        Stack<String> solution = new Stack<>();

        //first move will always be forward
        runner.move(maze[runner.currentPosition.yCord][runner.currentPosition.xCord + 1]);
        solution.push("F");
        runner.lastMovement = "F";

        //find end of maze
        while (!Objects.equals(runner.currentPosition, end)) {
            runner.currentPosition.passed = true;
            Tile R = maze[runner.currentPosition.yCord + 1][runner.currentPosition.xCord]; //tile right
            Tile F = maze[runner.currentPosition.yCord][runner.currentPosition.xCord + 1]; //tile forward
            Tile L = maze[runner.currentPosition.yCord - 1][runner.currentPosition.xCord]; //tile left
            Tile B = maze[runner.currentPosition.yCord][runner.currentPosition.xCord - 1]; //tile backward

            if (Objects.equals(R.type,"PATH") && !(Objects.equals(runner.lastMovement,"L") || Objects.equals(runner.lastMovement,"B")) && !Objects.equals(F,end)){
                runner.move(R);
                solution.push("R");
                runner.lastMovement = "R";
            } else if (Objects.equals(F.type,"PATH")&& !Objects.equals(runner.lastMovement,"B")) {
                runner.move(F);
                solution.push("F");
                runner.lastMovement = "F";
            } else if (Objects.equals(L.type,"PATH")) {
                runner.move(L);
                if (L.passed){solution.pop();} else{ solution.push("L");}
                runner.lastMovement = "L";
            } else {
                runner.move(B);
                if (B.passed){solution.pop();} else{ solution.push("B");}
                runner.lastMovement = "B";
            }
        }

        return solution;
    }

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
