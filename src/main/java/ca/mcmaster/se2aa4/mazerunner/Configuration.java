package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.Logger;

import java.util.Objects;
import java.util.Scanner;

public class Configuration {

    private String[] args = null;
    private Options options = new Options();
    private CommandLineParser parser = new DefaultParser();

    public String inputFile = null;
    public String inputPath = null;

    public String mode = "MazeSolver";


    public Configuration(String[] args) {
        this.args = args;
        options.addOption("i","input",true,"input file");
        options.addOption("p",true,"input path");

    }

    public void setInputFile() throws ParseException{
        CommandLine cmd = parser.parse(options, args);
        this.inputFile = cmd.getOptionValue("input","./examples/small.maz.txt");
    }

    public void setNewInputFile(String newInputFile){
        this.inputFile = newInputFile;
    }

    public void setInputPath() throws ParseException{
        CommandLine cmd = parser.parse(options, args);
        this.inputPath = cmd.getOptionValue("p","EMPTY");
    }

    public void setNewInputPath(String newInputPath){
        this.inputPath = newInputPath;
    }

    public void setMode(){
        if (!Objects.equals(inputPath,"EMPTY")){
            this.mode = "COMPARE";
        }
    }

    public boolean validPath(String inputPath, String mazeSolution) {return Objects.equals(inputPath,mazeSolution);}
}
