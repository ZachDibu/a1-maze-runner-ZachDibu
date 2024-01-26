package ca.mcmaster.se2aa4.mazerunner;
//keeps track of the person's current position as they move through the maze
public class MazeRunner {
    Tile currentPosition;
    public String direction = "EAST";
    public MazeRunner(Tile start) {
        this.currentPosition = start;
    }

    //updates currentPosition to new tile
    public void move(Tile newPosition) {this.currentPosition = newPosition; }

}
