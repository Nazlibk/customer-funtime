package ie.interview;

import com.beust.jcommander.Parameter;

public class RunArgs {

    @Parameter(names = {"--input", "-i"}, description = "input file that contains json record for customers")
    private String inputFileName;

    @Parameter(names = {"--help", "-h"}, help = true)
    private boolean help = false;

    public String getInputFileName() {
        return inputFileName;
    }

    public boolean isHelp() {
        return help;
    }
}
