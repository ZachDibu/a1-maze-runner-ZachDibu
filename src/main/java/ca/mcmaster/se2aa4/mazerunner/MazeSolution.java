package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public class MazeSolution extends Maze {
    public String canonicalSolution;
    public String reverseCanonicalSolution;
    public String factorizedSolution;
    public String reverseFactorizedSolution;
    public MazeSolution(String inputFile) {
        super(inputFile);
    }

    public void canonicalSolution(Tile[][] maze, Tile start, Tile end) {
        PrimAlg mazeAlgorithm = new PrimAlg(maze, start, end);
        Stack<String> stackSolution = mazeAlgorithm.solveMaze();

        StringBuilder strSolution = new StringBuilder();

        for (String s : stackSolution) {
            strSolution.append(s);
        }
        canonicalSolution = strSolution.toString();
    }

    public String factorizeSolution(String canonicalSolution){
        StringBuilder factSol = new StringBuilder();
        int count = 1;
        for (int i = 0, j = 1; j < canonicalSolution.length(); i++, j++){
            if (canonicalSolution.charAt(j) == canonicalSolution.charAt(i)){
                count++;
            }else{
                factSol.append(count).append(canonicalSolution.charAt(i)).append(" ");
                count = 1;
            }
        }
        factSol.append(count).append(canonicalSolution.charAt(canonicalSolution.length()-1));
        return factSol.toString();
    }

    //takes into account the direction a person would be facing as travelling though a maze.
// R and L only turn, F moves forward
    public void convertCanonical() {
        StringBuilder newCanon = new StringBuilder();
        String direction = "EAST";

        for (int i = 0; i < canonicalSolution.length(); i++){
            switch (canonicalSolution.charAt(i)) {
                case 'F':
                    switch (direction){
                        case "EAST": newCanon.append("F"); break;
                        case "NORTH": newCanon.append("RF"); break;
                        case "WEST": newCanon.append("LLF"); break;
                        case "SOUTH": newCanon.append("LF"); break;
                    }
                    direction = "EAST";
                    break;
                case 'L':
                    switch (direction){
                        case "EAST": newCanon.append("LF"); break;
                        case "NORTH": newCanon.append("F"); break;
                        case "WEST": newCanon.append("RF"); break;
                        case "SOUTH": newCanon.append("LLF"); break;
                    }
                    direction = "NORTH";
                    break;
                case 'R':
                    switch (direction){
                        case "EAST": newCanon.append("RF"); break;
                        case "NORTH": newCanon.append("LLF"); break;
                        case "WEST": newCanon.append("LF"); break;
                        case "SOUTH": newCanon.append("F"); break;
                    }
                    direction = "SOUTH";
                    break;
                case 'B':
                    switch (direction){
                        case "EAST": newCanon.append("LLF"); break;
                        case "NORTH": newCanon.append("LF"); break;
                        case "WEST": newCanon.append("F"); break;
                        case "SOUTH": newCanon.append("RF"); break;
                    }
                    direction = "WEST";
                    break;
            }
        }
        this.canonicalSolution = newCanon.toString();
    }

    public void reverseCanonical() {
        StringBuilder reverseCanonical = new StringBuilder();
        for (int i = canonicalSolution.length()-1; i >= 0; i--){
            if (canonicalSolution.charAt(i) == 'R'){
                reverseCanonical.append("L");
            } else if (canonicalSolution.charAt(i) == 'L'){
                reverseCanonical.append("R");
            } else{
                reverseCanonical.append(canonicalSolution.charAt(i));
            }
        }
        this.reverseCanonicalSolution = reverseCanonical.toString();
    }
}
