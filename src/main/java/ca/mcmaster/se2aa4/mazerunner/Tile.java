package ca.mcmaster.se2aa4.mazerunner;

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
