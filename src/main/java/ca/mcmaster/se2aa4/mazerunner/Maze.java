package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Maze {

    public Tile start;
    public Tile end;
    public String inputFile;
    public int width = 0;
    public int height = 0;

    public Tile[][] mazeArray;

    public Maze(String inputFile) {
        this.inputFile = inputFile;
    }


    public void printMaze() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();
        this.width = line.length();

        while (line != null) {
            for (int idx = 0; idx < line.length(); idx++) {
                if (line.charAt(idx) == '#') {
                    System.out.print("# ");
                } else if (line.charAt(idx) == ' ') {
                    System.out.print("  ");
                }
            }
            System.out.print(System.lineSeparator());
            this.height++;
            line = reader.readLine();
        }
        reader.close();
    }


    public void mazeArray() throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        Tile[][] maze = new Tile[height][width];
        String line = reader.readLine();
        int y = 0;
        while (line != null && y < height){
            if (line.isEmpty() || line.isBlank()) {
                for (int x = 0; x < width; x++) {
                    maze[y][x] = new Tile("PATH", y, x);
                }
            }
            for (int x = 0; x < line.length() && x < width; x++) {
                if (line.charAt(x) == '#') {
                    maze[y][x] = new Tile("WALL",y,x);
                } else if (line.charAt(x) == ' ') {
                    maze[y][x] = new Tile("PATH",y,x);
                }
            }
            y++;
            line = reader.readLine();
        }
        reader.close();
        this.mazeArray =  maze;
    }

    public void getStart(Tile[][] mazeArray) {
        int x = 0;
        int y = 0;
        while (!mazeArray[y][x].type.equals("PATH")){
            y++;
        }
        this.start = mazeArray[y][x];
    }

    public void getEnd(Tile[][] mazeArray) {
        int x = width-1;
        int y = 0;
        while (!mazeArray[y][x].type.equals("PATH")){
            y++;
        }
        this.end = mazeArray[y][x];
    }

}
