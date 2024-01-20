package ca.mcmaster.se2aa4.mazerunner;

import java.util.Objects;
import java.util.Stack;

public class PrimAlg {
    Tile start;
    Tile end;

    private Tile[][] maze;

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
}
