package ca.mcmaster.se2aa4.mazerunner;

public class MazeRunner {
    Tile currentPosition = null;
    public MazeRunner(Tile start) {
        this.currentPosition = start;
    }

    //updates currentPosition to new tile
    public void moveR() {
        this.currentPosition.yCord = currentPosition.yCord - 1;
    }

    public void moveF() {
        this.currentPosition.xCord = currentPosition.yCord + 1;
    }

    public void moveL() {
        this.currentPosition.yCord = currentPosition.yCord + 1;
    }

    public void moveB() {
        this.currentPosition.yCord = currentPosition.xCord - 1;
    }
}
