package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class PrimAlg {
    Tile start;
    Tile end;

    private ArrayList<Tile>[][] maze;

    public PrimAlg(ArrayList<Tile>[][] maze, Tile start, Tile end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    //Assuming runner would not have to move back
    public Stack<String> solveMaze(){
        MazeRunner runner = new MazeRunner(start);
        Stack<String> solution = new Stack<>();
        while (!Objects.equals(runner.currentPosition, end)) {
            runner.currentPosition.passed = true;

            Tile R = maze[runner.currentPosition.xCord][runner.currentPosition.yCord - 1].get(0); //tile right
            Tile F = maze[runner.currentPosition.xCord + 1][runner.currentPosition.yCord].get(0); //tile forward
            Tile L = maze[runner.currentPosition.xCord][runner.currentPosition.yCord + 1].get(0); //tile left
            Tile B = maze[runner.currentPosition.xCord - 1][runner.currentPosition.yCord].get(0); //tile backward

            if (Objects.equals(R.type,"PATH") && !Objects.equals(runner.lastMovement,"L") && !Objects.equals(F,end)){
                runner.move(R);
                solution.push("R");
                runner.lastMovement = "R";
            } else if (Objects.equals(F.type,"PATH")) {
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
