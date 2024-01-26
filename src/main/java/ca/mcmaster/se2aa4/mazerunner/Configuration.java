package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.ParseException;
import java.util.Objects;

public class Configuration {

    private final String[] args;
    private final Options options = new Options();
    private final CommandLineParser parser = new DefaultParser();

    public String inputFile = null;
    public String inputPath = null;

    public String mode = "MazeSolver";


    public Configuration(String[] args) {
        this.args = args;
        options.addOption("i","input",true,"input file");
        options.addOption("p",true,"input path");

    }

    //finds the input file
    public void setInputFile() throws ParseException{
        CommandLine cmd = parser.parse(options, args);
        String input = cmd.getOptionValue("input","./examples/small.maz.txt");
        input = input.replace("\"", "");
        this.inputFile = input;
    }

    //finds the input path
    public void setInputPath() throws ParseException{
        CommandLine cmd = parser.parse(options, args);
        String input = cmd.getOptionValue("p","EMPTY");
        input = input.replace("\"", "");
        input = input.replace(" ", "");
        this.inputPath = input;
    }

    //sets the initial mode based on the presence of an input path
    public void setMode(){
        if (!Objects.equals(inputPath,"EMPTY")){
            this.mode = "COMPARE";
        }
    }
}
