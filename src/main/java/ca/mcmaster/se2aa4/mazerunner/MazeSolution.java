package ca.mcmaster.se2aa4.mazerunner;

import java.util.Stack;

public class MazeSolution extends Maze {
    public String canonicalSolution;
    public String reverseCanonicalSolution;
    public String factorizedSolution;
    public String reverseFactorizedSolution;
    public String inputCanonical;

    public MazeSolution(String inputFile) {
        super(inputFile);
    }

    //finds the canonical solution of a maze using a MazeAlgorithm.
    public void canonicalSolution(Tile[][] maze, Tile start, Tile end) {
        MazeAlgorithm mazeAlgorithm = new RightHandAlgorithm(maze, start, end);
        Stack<String> stackSolution = mazeAlgorithm.solveMaze();

        StringBuilder strSolution = new StringBuilder();

        for (String s : stackSolution) {
            strSolution.append(s);
        }
        canonicalSolution = strSolution.toString();
    }

    //factorizes a canonical solution
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

    //reverses a canonical solution.
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

    //Converts an input path into a canonical form for checking
    public void convertCanonical(String inputPath){
        StringBuilder newPath = new StringBuilder();
        for (int i = 0; i < inputPath.length(); i++){
            if (Character.isDigit(inputPath.charAt(i))){
                int num = Character.getNumericValue(inputPath.charAt(i));
                char nextChar = inputPath.charAt(i+1);
                while (Character.isDigit(nextChar)){
                    num = num*10+Character.getNumericValue(nextChar);
                    i++;
                    nextChar = inputPath.charAt(i+1);
                }
                for (int j = num - 1 ; j > 0; j--){
                    newPath.append(nextChar);
                }
            }else{
                newPath.append(inputPath.charAt(i));
            }
        }
        inputCanonical = newPath.toString();
    }
}
