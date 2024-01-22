package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {
    Tile currentPosition;
    public String lastMovement = null;
    public MazeRunner(Tile start) {
        this.currentPosition = start;
    }

    //updates currentPosition to new tile
    public void move(Tile newPosition) {this.currentPosition = newPosition; }

}
