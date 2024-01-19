package ca.mcmaster.se2aa4.mazerunner;

public class Tile {
    public String type;
    public int xCord;
    public int yCord;

    public boolean passed = false;

    public Tile(String tileType, int xCord, int yCord) {
        this.type = tileType;
        this.xCord = xCord;
        this.yCord = yCord;
    }
}
