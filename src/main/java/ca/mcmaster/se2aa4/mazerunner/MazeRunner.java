package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {
    Tile currentPosition;
    public String lastMovement = "F";
    public String direction = "EAST";
    public MazeRunner(Tile start) {
        this.currentPosition = start;
    }

    //updates currentPosition to new tile
    public void move(Tile newPosition) {this.currentPosition = newPosition; }

}
