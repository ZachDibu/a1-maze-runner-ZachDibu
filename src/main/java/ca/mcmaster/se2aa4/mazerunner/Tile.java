package ca.mcmaster.se2aa4.mazerunner;
/*
Contains information about "Tiles," which are coordinates on the "grid." the grid being the 2D matrix. Each tile can
either be a WALL or a PATH. if it is a PATH then the MazeRunner can move to it.
 */
public class Tile {
    public String type;
    public int xCord;
    public int yCord;

    public boolean passed = false;

    public Tile(String tileType, int yCord, int xCord) {
        this.type = tileType;
        this.yCord = yCord;
        this.xCord = xCord;
    }
}
