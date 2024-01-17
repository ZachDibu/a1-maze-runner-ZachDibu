package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;

import java.util.Objects;

public class Configuration {

    private String[] args = null;
    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();


    public Configuration(String[] args) {
        this.args = args;
        options.addOption("i","input",true,"input file");
        options.addOption("p",true,"input path");

    }

    public String inputFile() throws ParseException{
        String inputFile = null;
        CommandLine cmd = parser.parse(options, args);
        inputFile = cmd.getOptionValue("input","./examples/small.maz.txt");
        return inputFile;
    }

    public String inputPath() throws ParseException{
        String inputPath = null;
        CommandLine cmd = parser.parse(options, args);
        inputPath = cmd.getOptionValue("p");
        return inputPath;
    }


    public boolean validPath(Object inputPath, Object mazeSolution) {
        return Objects.equals(inputPath,mazeSolution);
    }
}
