package ca.mcmaster.se2aa4.mazerunner;

import java.util.ArrayList;

public class PrimAlg {
    public String solution = null;
    Tile start;
    Tile end;

    private ArrayList<Tile>[][] maze;

    public PrimAlg(ArrayList<Tile>[][] maze, Tile start, Tile end) {
        this.maze = maze;
        this.start = start;
        this.end = end;
    }

    public String solveMaze(){
        MazeRunner runner = new MazeRunner(start);
        runner.moveR();
        runner.moveF();
        runner.moveL();
        runner.moveB();
        //do until runner.currentPosition != end

        return solution;
    }
}
