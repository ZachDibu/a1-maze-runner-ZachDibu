package ca.mcmaster.se2aa4.mazerunner;

public class Configuration {

    private String[] args;

    public Configuration(String[] args) {
        this.args = args;
    }

    public Object inputFile() {
        String inputFile = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-i") || args[i].equals("--input")) {
                if (i + 1 < args.length) {
                    inputFile = args[i + 1];
                    break;
                }
            }
        }
        return inputFile;
    }

    public Object inputPath() {
        String inputPath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-p")) {
                if (i + 1 < args.length) {
                    inputPath = args[i + 1];
                    break;
                }
            }
        }
        return inputPath;
    }

    public boolean validPath(Object inputPath, Object mazeSolution) {
        return false;
    }
}
